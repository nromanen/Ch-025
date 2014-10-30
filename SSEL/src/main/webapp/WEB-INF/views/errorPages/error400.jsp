<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <meta name='viewport' content='width=device-width, initial-scale=1.0' />
  <title>Bad request (400)</title>
        <style type="text/css">
                body { background-color: #fff; color: #000; font-family: arial, sans-serif; }
                h1 { font-size: 400%; line-height: 1em; margin: 0; padding: 0;}
                h2 { font-size: 120%; margin: 0; padding: 0; text-transform: uppercase;}
                a { color: #03a8d1; }
                .logo { border: 0; margin-bottom: 40px; }
                body { padding: 20px; }
                @media screen and (min-width: 550px) {
                  .logo {
                  margin-bottom: 8px;
                  }
                .content {
                  width: 530px;
        margin: 65px auto 0 auto;
                }
      .grey-frame {
        -moz-border-radius: 8px;
        border: 5px solid #f3f5f0;
      }
      .grey-frame-inner {
        -moz-border-radius: 3px;
        border: 1px solid #cbd4bf;
        padding: 25px;
      }
    }
        </style>
</head>
<body>
  <div class="content">
    <div class="grey-frame">
      <div class="grey-frame-inner">
        <h1>400</h1>
        <h2>Bad request</h2>
        <p>We apologize but request was invalid or cannot be otherwise served.</p>
        <p>Would you like to: <a href="javascript: history.go(-1)">Go back</a> or <a href="${pageContext.request.contextPath}/">go to home page</a>?</p>
      </div>
    </div>
  </div>
</body>
</html>