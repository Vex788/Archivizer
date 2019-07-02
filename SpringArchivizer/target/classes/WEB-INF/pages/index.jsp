<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Archivizer</title>
    <style>
        body {
            background-color: black;
            text-align: center;
            color: white;
            font-family: roboto light;
            background: url(http://getwallpapers.com/wallpaper/full/b/d/0/606321.jpg) black;
        }

        .container {
            margin: 25%;
            margin-left: 30%;
            margin-right: 30%;
            border-radius: 10px;
            padding: 10px;
            background: black;
            border: 0px solid black;
            text-align: center;
            opacity: 0.80;
        }

        .upload-btn-wrapper {
            position: relative;
            overflow: hidden;
            display: inline-block;
        }

        .btn {
            border: 1px solid gray;
            color: white;
            background-color: black;
            padding: 8px 20px;
            border-radius: 8px;
            font-size: 20px;
            -webkit-transition: all 0.3s ease;;
            -moz-transition: all 0.3s ease;;
            -o-transition: all 0.3s ease;;
            transition: all 0.3s ease;
        }

        .upload-btn-wrapper input[type=file] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }

        .upload-btn-wrapper input[type=submit] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }

    </style>
</head>
<body>

<div class="container">
    <div style="padding:5px; color:red; font-style:italic;">
        ${error_message}
    </div>
    <div style="padding:5px; color:green; font-style:italic;">
        ${success_message}
    </div>

    <h2>Archivizer</h2>
    <h4>Archive your files</h4>

    <form method="post" action="/upload" enctype="multipart/form-data">

        <div class="upload-btn-wrapper">
            <button class="btn">Select your files</button>
            <input type="file" name="file" multiple="multiple"/>
        </div>
        <br/>
        <br/>
        <div class="upload-btn-wrapper">
            <button class="btn">Upload</button>
            <input type="submit" value="Upload"/>
        </div>
    </form>
</div>

</body>
</body>
</html>
