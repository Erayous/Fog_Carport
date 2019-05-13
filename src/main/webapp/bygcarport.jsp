﻿<%@include file="include/header.jsp"%>

    <main class="page contact-us-page"></main>
    <h1 style="margin-top: 120px;margin-bottom: 50px;">Byg din egen carport</h1>
    <div>
        <div class="container" style="margin-bottom: 50px;">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body"><img id="PicturePreview" style="width:100%;height: 15vw;object-fit: cover;" src="assets\img\garager\enkelt.jpg"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card"></div>
                    <div class="card">
                        <div class="card-header">
                            <ul class="nav nav-tabs card-header-tabs">
                                <li class="nav-item"><a class="nav-link active" href="#carport">Carport</a></li>
                                <li class="nav-item"><a class="nav-link" href="#taget">Taget</a></li>
                                <li class="nav-item"><a class="nav-link" href="#afslut">Bekræftelse</a></li>
                            </ul>
                        </div>
                        <div class="card-body">
                            <label><b>Angiv størrelse på carport</b></label>
                            <div class="row" style="margin-bottom: 35px;">
                                <div class="col-xl-6" style="min-height: 50px;"><button class="btn btn-primary" id="Enkeltknap" type="button" onclick="Enkelt()" style="min-width: 100%;">Enkelt</button></div>
                                <div class="col"><button class="btn btn-primary" id="Dobbeltknap" type="button" onclick="Dobbelt()" style="min-width: 100%;">Dobbelt</button></div>
                            </div>

                            <label><b>Angiv om du ønsker skur.</b></label>
                            <div class="row" style="margin-bottom: 35px;">
                                <div class="col-xl-6" style="min-height: 50px;">
                                    <button class="btn btn-primary" id="Medskurknap" type="button" onclick="MedSkur()" style="min-width: 100%;">Med skur</button>
                                </div>
                                <div class="col">
                                    <button class="btn btn-primary" id="Udenskurknap" type="button" onclick="UdenSkur()" style="min-width: 100%;">Uden skur</button>
                                </div>
                            </div>

                            <div id="skurCover" style="display:none">
                                <label><b>Vælg skurbeklædning</b></label>
                                <div class="row" style="margin-bottom: 35px;">
                                    <div class="col-xl-6" style="min-height: 50px;">
                                        <button class="btn btn-primary" type="button" style="min-width: 100%;">En på to</button>
                                    </div>
                                    <div class="col">
                                        <button class="btn btn-primary" type="button" style="min-width: 100%;">Klinkbeklædning</button>
                                    </div>
                                </div>
                            </div>

                            <label><b>Angiv mål på carport</b></label>
                            <div class="row">
                                <div class="col"><label>Længde (cm):</label><input class="form-control" type="text" style="min-width: 100%;margin-bottom: 15px;"></div>
                            </div>
                            <div class="row" style="margin-bottom: 15px;">
                                <div class="col"><label>Bredde (cm):</label><input class="form-control" type="text" style="min-width: 100%;"></div>
                            </div>
                            <div class="row" style="margin-bottom: 15px;">
                                <div class="col"><label>Højde (cm):</label><input class="form-control" type="text" style="min-width: 100%;"></div>
                            </div>

                            <div id="skurCM" style="display:none">
                                <label><b>Angiv mål på skur</b></label>
                                <div class="row">
                                    <div class="col"><label>Længde (cm):</label>
                                        <input class="form-control" type="text" style="min-width: 100%;margin-bottom: 15px;">
                                    </div>
                                </div>
                                <div class="row" style="margin-bottom: 15px;">
                                    <div class="col"><label>Bredde (cm):</label>
                                        <input class="form-control" type="text" style="min-width: 100%;">
                                    </div>
                                </div>
                            </div>



                            <button class="btn btn-primary" type="button" style="float: right;">Næste</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script>
    var variableEnkelt = 0;
    var variableDobbelt = 0;
    var variableSkur = 0;

    function Enkelt() {
        document.getElementById('Enkeltknap').style.backgroundColor = "#074180";
        document.getElementById('Dobbeltknap').style.backgroundColor = "#007bff";

        document.getElementById('Medskurknap').style.backgroundColor = "#007bff";
        document.getElementById('Udenskurknap').style.backgroundColor = "#007bff";
        if (variableDobbelt == 1){
            variableDobbelt = 0;
            variableEnkelt = 1;
        }else{
            variableEnkelt = 1;
        }
        document.getElementById('PicturePreview').src = "assets/img/garager/enkelt.jpg";
    }

    function Dobbelt() {
        document.getElementById('Enkeltknap').style.backgroundColor = "#007bff";
        document.getElementById('Dobbeltknap').style.backgroundColor = "#074180";

        document.getElementById('Medskurknap').style.backgroundColor = "#007bff";
        document.getElementById('Udenskurknap').style.backgroundColor = "#007bff";


        if(variableEnkelt == 1){
            variableEnkelt = 0;
            variableDobbelt = 1;
        }else{
            variableDobbelt = 1;
        }
        document.getElementById('PicturePreview').src = "assets/img/garager/dobbelt.jpg";
    }

    function MedSkur() {

        document.getElementById("skurCover").style.display = "block";
        document.getElementById("skurCM").style.display = "block";

        document.getElementById('Medskurknap').style.backgroundColor = "#074180";
        document.getElementById('Udenskurknap').style.backgroundColor = "#007bff";

        if(variableEnkelt == 1){
            variableSkur = 1;
            document.getElementById('PicturePreview').src = "assets/img/garager/enkeltmskur.jpg";
        }else{
            variableSkur = 1;
            document.getElementById('PicturePreview').src = "assets/img/garager/dobbeltmskur.jpg";
        }
    }

    function UdenSkur() {

        document.getElementById("skurCover").style.display = "none";
        document.getElementById("skurCM").style.display = "none";

        document.getElementById('Medskurknap').style.backgroundColor = "#007bff";
        document.getElementById('Udenskurknap').style.backgroundColor = "#074180";
        if(variableEnkelt == 1){
            variableSkur = 0;
            document.getElementById('PicturePreview').src = "assets/img/garager/enkelt.jpg";
        }else{
            document.getElementById('PicturePreview').src = "assets/img/garager/dobbelt.jpg";
        }
    }

</script>

<%@include file="include/footer.jsp"%>