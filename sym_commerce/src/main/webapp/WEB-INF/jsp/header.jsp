<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Symbiosis</title>
    <script src="js/jquery.js"></script>
    <script type="text/javascript">

        var blink_count = 9;
        var blink_rate = 200;
        var blink_loop_array = [];

        function blinkIt()
        {
            for(var i = 0; i < document.getElementsByTagName('emphasize').length; i++)
            {
                var s = document.getElementsByTagName('emphasize')[i];
                s.style.visibility = (s.style.visibility=='visible') ? 'hidden' : 'visible';
            }

            if (--blink_count <= 0)
            {
                //clear all intervals in case multiple wer started simultaneously
                for (var c = 0; c < blink_loop_array.length; c++)
                {
                    clearInterval(blink_loop_array.pop());
                }
                blink_count = 9;
                document.getElementsByTagName('emphasize').visibility = 'visible';
            }
        }

        function doAjaxPost()
        {
            var menu_response = $('#menu_response').val();

            $.ajax({
                type: "POST",
                url: "http://localhost:8080/sym_commerce/process",
                data: "menu_response=" + menu_response,
                success: function(response)
                {
                    // we have the response
                    $('#gameMenu').append('<br/>' + response);
                    $('#menu_response').val('');
                    blink_loop_array.push(setInterval(blinkIt,blink_rate));
                },
                error: function(e)
                {
                    alert('Error: ' + e);
                }
            });

            document.getElementById("menu_response").focus();
        }

        function checkSubmit(e)
        {
            if(e && e.keyCode == 13)
            {
                doAjaxPost();
            }
        }

    </script>

</head>
<body style="background: #9dd5ff; color: #FFFFFF;" >
<div align="center">

