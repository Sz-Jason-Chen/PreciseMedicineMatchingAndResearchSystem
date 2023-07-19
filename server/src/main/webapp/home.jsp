<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <div style="width:1500px;float:left;">
        <h2>Simple query</h2>
        <div style="width:1000px;float:left;">
            <h3>Drug label annotations</h3>
            <div style="width:500px;float:left;">
                <h4>By drug name</h4>
                Get label annotation information of one drug.<br/>
                <form action="DrugNameLabelServlet" method="post">
                    Drug name: <input type="text" name="name" value="atorvastatin"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
            <div style="width:500px;float:left;">
                <h4>By gene symbol</h4>
                Get all label annotations which related to the gene.
                <form action="DrugGeneLabelServlet" method="post">
                    Gene (symbol): <input type="text" name="gene" value="ABL1"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
        </div>
        <div style="width:1000px;float:left;">
            <h3>Drug clinical annotations</h3>
            <div style="width:500px;float:left;">
                <h4>By drug name</h4>
                Get clinical annotation information of one drug.<br/>
                <form action="DrugNameClinicalServlet" method="post">
                    Drug name: <input type="text" name="name" value="buprenorphine"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
            <div style="width:500px;float:left;">
                <h4>By gene symbol</h4>
                Get all clinical annotations which related to the gene.
                <form action="DrugGeneClinicalServlet" method="post">
                    Gene (symbol): <input type="text" name="gene" value="ACE2"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
        </div>
        <div style="width:1000px;float:left;">
            <h3>Query reference</h3>
            <div style="width:500px;float:left;">
                <h4>By related gene</h4>
                Get all references which related to the gene.
                <form action="GeneReferenceServlet" method="post">
                    Gene (symbol): <input type="text" name="gene" value="FMO3"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
        </div>
        <div style="width:1000px;float:left;">
            <h3>Query pathway information</h3>
            <div style="width:500px;float:left;">
                <h4>By pathway ID</h4>
                Get the pathway's basic informations.
                <form action="PathwayIdServlet" method="post">
                    Pathway ID: <input type="text" name="id" value="PA2023"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
            <div style="width:500px;float:left;">
                <h4>By related gene</h4>
                Get all pathways whose entities include the gene.
                <form action="PathwayGeneServlet" method="post">
                    Gene (symbol): <input type="text" name="gene" value="TGFB1"><br/>
                    <input type="submit" value="search">
                </form>
            </div>
        </div>
    </div>
    <div style="width:1500px;float:left;">
        <h2>Clinical matching</h2>
        <div style="width:1000px;float:left;">
            <h3>Drug matching</h3>
            <div style="width:500px;float:left;">
                <h4>Original vcf matching</h4>
                <form action="DrugRawMatchingServlet" method="post" enctype="multipart/form-data">
                    <p>VCF file: <br/></p>
                    <input type="file" name="file"><br/>
                    <input type="submit" value="upload">
                </form>
            </div>
            <div style="width:500px;float:left;">
                <h4>Annotated txt matching</h4>
                <form action="DrugAnnotatedMatchingServlet" method="post" enctype="multipart/form-data">
                    <p>TXT file: <br/></p>
                    <input type="file" name="file"><br/>
                    <input type="submit" value="upload">
                </form>
            </div>
        </div>
        <div style="width:1000px;float:left;">
            <h3>Pathway matching</h3>
            <div style="width:500px;float:left;">
                <h4>Original vcf matching</h4>
                <form action="PathwayRawMatchingServlet" method="post" enctype="multipart/form-data">
                    <p>VCF file: <br/></p>
                    <input type="file" name="file"><br/>
                    <input type="submit" value="upload">
                </form>
            </div>
            <div style="width:500px;float:left;">
                <h4>Annotated txt matching</h4>
                <form action="PathwayAnnotatedMatchingServlet" method="post" enctype="multipart/form-data">
                    <p>TXT file: <br/></p>
                    <input type="file" name="file"><br/>
                    <input type="submit" value="upload">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
