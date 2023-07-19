import csv
import requests
import json
import re

def getText(url, headers={}):
    html = requests.get(url=url, headers=headers)
    return html.text

def getPathwayIDs():
    raw_text = getText(url="https://api.pharmgkb.org/v1/site/pathways")
    parsed_text = json.loads(raw_text)["data"]["hits"]
    pathway_ids = []
    for pathway in parsed_text:
        pathway_ids.append(pathway["id"])
    return pathway_ids

def getPathwayInfo(pathway_ids):
    url = "https://api.pharmgkb.org/v1/site/pathway/{}"
    rows = [["id", "name", "chemicals", "genes", "pharmacodynamic", "pharmacokinetic"]]
    for id in pathway_ids:
        id_url = url.format(id)
        text = getText(id_url)
        data = json.loads(text)["data"]
        pathway = data["pathway"]
        if "," in pathway["name"]:
            name = re.split(", ", pathway["name"])[0]
        else:
            name = pathway["name"]
        chemicals_list = pathway["chemicals"]
        chemicals = ""
        for chemical in chemicals_list:
            chemicals = chemicals + chemical["name"] + ", "
        chemicals = chemicals[:-2]
        genes_list = pathway["genes"]
        genes = ""
        for gene in genes_list:
            genes = genes + gene["symbol"] + ", "
        genes = genes[:-2]
        if pathway["pharmacodynamic"]:
            pharmacodynamic = 1
        else:
            pharmacodynamic = 0
        if pathway["pharmacokinetic"]:
            pharmacokinetic = 1
        else:
            pharmacokinetic = 0


        """if "description" in pathway:
            description = pathway["description"]["html"]
            description = re.findall(r"<p>(.+?)</p>", pathway["description"]["html"])
            while "<" in description:
                tag = re.findall(r"<(.+?)>", description)
                description = re.findall()"""

        rows.append([id, name, chemicals, genes, pharmacodynamic, pharmacokinetic])
        print(id)

    with open('pathway.csv', 'w', encoding='utf8', newline='') as f:
        writer = csv.writer(f)
        writer.writerows(rows)

def main():
    pathway_ids = getPathwayIDs();
    getPathwayInfo(pathway_ids)

if __name__ == "__main__":
    main()
