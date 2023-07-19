import requests
import os
import re
from fake_useragent import UserAgent
import csv

class PharmgkbSpider(object):

    def __init__(self):
        self.url = "https://api.pharmgkb.org/v1/site/labelsByDrug"
        self.second_url = 'https://api.pharmgkb.org/v1/site/page/drugLabels/{}?view=base'
        userAgent = UserAgent()
        self.headers = {'User-Agent': userAgent.random}

    # 获取药物数据
    def get_drug(self,url):

        res = requests.get(url,headers = self.headers)
        # 更改编码格式
        res.encoding ="utf-8"
        # 得到一级地址
        html1 = res.text
        pattern_drugID = re.compile('"drug":{"objCls":"Chemical","id":"(.*?)","name',re.S)
        ID_list = pattern_drugID.findall(html1)
        print(len(ID_list))
        data_list = []

        for ID in ID_list:
            # 拼接二级地址
            print(ID)
            second_url = self.second_url.format(ID)
            res2 = requests.get(second_url,headers = self.headers)
            res2.encoding ="utf-8"
            html2 = res2.text
            pattern_drugLabelID = re.compile('"Label Annotation","id":"(.*?)","name"',re.S)
            LabelID_list = pattern_drugLabelID.findall(html2)
            pattern_gene = re.compile('"relatedGenes":.*?"symbol":"(.*?)","name"',re.S)
            gene_list = pattern_gene.findall(html2)
            if len(gene_list)<len(LabelID_list):
                for i in range(0,len(LabelID_list)-len(gene_list)-1):
                    gene_list.append("")
            pattern_source = re.compile('"source":"(.*?)","summaryMarkdown"',re.S)
            source_list = pattern_source.findall(html2)
            pattern_summaryMarkdown = re.compile('summaryMarkdown":.*?"html":"<p>(.*?)</p>',re.S)
            summaryMarkdown_list = pattern_summaryMarkdown.findall(html2)
            print(len(LabelID_list))
            print(len(gene_list))
            print(len(source_list))
            print(len(summaryMarkdown_list))
            for i in range(0,len(LabelID_list)-1):
                sample_list = [ID, LabelID_list[i], gene_list[i], source_list[i], summaryMarkdown_list[i]]
                data_list.append(sample_list)

            # 保存
            nowpath = os.getcwd()
            filename = 'drugdata.csv'
            with open('drugdata.csv','w',encoding='utf8',newline='') as f :
                writer = csv.writer(f)
                writer.writerows(data_list)
            print("save")

    # 入口函数 
    def run(self):
        self.get_drug(self.url)


os.chdir("D:\林致远\大二下\DST\project")
PS = PharmgkbSpider()
PS.run()