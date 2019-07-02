<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <style>
        body {
            background-color: black;
            text-align: center;
            color: white;
            -webkit-font-smoothing: antialiased;
            font-family: roboto light;
            background: url(http://getwallpapers.com/wallpaper/full/b/d/0/606321.jpg) white fixed;
        }

        label {
            display: inline-block;
            color: #fff;
            cursor: pointer;
            position: relative;
        }

        label .check-box-effect {
            display: inline-block;
            position: relative;
            background-color: transparent;
            width: 25px;
            height: 25px;
            border: 2px solid #dcdcdc;
            border-radius: 10%;
        }

        label .check-box-effect:before {
            content: "";
            width: 0px;
            height: 2px;
            border-radius: 2px;
            background: #626262;
            position: absolute;
            transform: rotate(45deg);
            top: 13px;
            left: 9px;
            transition: width 50ms ease 50ms;
            transform-origin: 0% 0%;
        }

        label .check-box-effect:after {
            content: "";
            width: 0;
            height: 2px;
            border-radius: 2px;
            background: #626262;
            position: absolute;
            transform: rotate(305deg);
            top: 16px;
            left: 10px;
            transition: width 50ms ease;
            transform-origin: 0% 0%;
        }

        label:hover .check-box-effect:before {
            width: 5px;
            transition: width 100ms ease;
        }

        label:hover .check-box-effect:after {
            width: 10px;
            transition: width 150ms ease 100ms;
        }

        input[type="checkbox"] {
            display: none;
        }

        input[type="checkbox"]:checked + .check-box-effect {
            background-color: aqua !important;
            transform: scale(1.25);
        }

        input[type="checkbox"]:checked + .check-box-effect:after {
            width: 10px;
            background: #333;
            transition: width 150ms ease 100ms;
        }

        input[type="checkbox"]:checked + .check-box-effect:before {
            width: 5px;
            background: #333;
            transition: width 150ms ease 100ms;
        }

        input[type="checkbox"]:checked:hover + .check-box-effect {
            background-color: #dcdcdc;
            transform: scale(1.25);
        }

        input[type="checkbox"]:checked:hover + .check-box-effect:after {
            width: 10px;
            background: #333;
            transition: width 150ms ease 100ms;
        }

        input[type="checkbox"]:checked:hover + .check-box-effect:before {
            width: 5px;
            background: #333;
            transition: width 150ms ease 100ms;
        }
        .container {
            margin-top: 10%;
            margin-left: 30%;
            margin-right: 30%;
            border-radius: 10px;
            padding: 20px;
            background: black;
            border: 0px solid black;
            text-align: center;
            opacity: 0.90;
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
            text-align: center;
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

        input[type=text] {
            border: 1px solid gray;
            color: black;
            font-size: 17px;
            background-color: white;
            padding: 8px 20px;
            border-radius: 8px;
            -webkit-transition: all 0.3s ease;;
            -moz-transition: all 0.3s ease;;
            -o-transition: all 0.3s ease;;
            transition: all 0.3s ease;
        }

        .upload-btn-wrapper input[type=submit] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }

        .photo-container {
            border: 1px solid gray;
            color: white;
            font-size: 17px;
            background-color: black;
            padding: 8px 20px;
            margin: 5px;
            border-radius: 8px;
        }

        div {
            text-align: -webkit-center;
        }

        img {
            width: 100%;
        }
        .ratio_image {
            height: 350px;
            position: relative;
            overflow: hidden;
        }

        .ratio_image:before {
            width: 100%;
            display: block;
            content: "";
        }

        .ratio_image img {
            min-width: 100%;
            min-height: 100%;
            position: absolute;
            top: 0;
            left: 0;
        }

        .bottom-corner {
            margin: 5px;
            width: 100%;
            border: 0px;
            position: absolute;
            bottom: 0px;
            right: 0px;
        }

        .bottoml-corner {
            margin: 5px;
            width: 100%;
            border: 0px;
            position: absolute;
            top: 0px;
            left: 0px;
        }

        h3 {
            z-index: 9;
            position: absolute;
            margin-top: 35px;
        }
    </style>
</head>
<body>
<div class="container">
    <c:if test="${photo_id == null}">
        <c:if test="${!photosId.isEmpty()}">
        <c:forEach var="photo" items="${photosId}">
        <div class="photo-container ratio_image">
            <h3>Your photo id is: <c:out value="${photo}"/></h3>
            <a href="/show_picture/${photo}"><img src="/photo/${photo}"/></a>
            <div class="bottom-corner">
                <input class="btn" type="submit" value="Delete Photo" onclick="window.location='/delete/${photo}';"/>
                <input class="btn" type="submit" value="Upload New" onclick="window.location='/';"/>
            </div>
            <label class="bottoml-corner">
                <input type="checkbox" name="delete" value="${photo}" id="chkProdTomove"  />
                <span class="check-box-effect"></span>
            </label>
        </div>
        </c:forEach>
        <input class="btn" onclick="deleteSelectedImage()" value="Delete selected"/>
        </c:if>
        </c:if>
    <c:if test="${photo_id != null}">
        <img src="/photo/${photo_id}"/>
    </c:if>
    <script>
        function deleteSelectedImage() {
            var checkboxes = document.getElementsByName('delete');
            var id_list = [];

            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked === true) {
                    id_list.push(checkboxes[i].value);
                }
            }

            window.location = '/delete_selected/' + id_list.toString();
        }
    </script>
</body>
</html>
