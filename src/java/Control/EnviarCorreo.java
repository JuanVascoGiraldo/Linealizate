
package Control;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




public class EnviarCorreo {
    
    private static String Link = "usu";
    
    public static void sendEmail(String recipient, String subject, String token, int act) {
        // Configuración de propiedades
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Reemplaza con el servidor SMTP que desees utilizar
        properties.put("mail.smtp.port", "587"); // Puerto del servidor SMTP

        // Credenciales del remitente
        String username = "quetzual@gmail.com"; // Reemplaza con tu dirección de correo electrónico
        String password = "ihdzyyhtekmqnsxi"; // Reemplaza con tu contraseña

        // Creación de la sesión de correo
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        String body =  "";
        
        if(act ==1){
            body= CorreoRegistro();
        }else{
            
        }

        try {
            // Creación del mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            // Envío del mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado exitosamente");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo electrónico");
            e.printStackTrace();
        }
    }
    
    
    public static String CorreoRegistro(){
        String correo= "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional"
                + "//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                + "style=\"font-family:georgia, times, 'times new roman', serif\"><head>"
                + "<meta charset=\"UTF-8\"><meta content=\"width=device-width, "
                + "initial-scale=1\" name=\"viewport\"><meta name=\"x-apple-disable-message-reformatting\">"
                + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta content=\"telephone=no\" "
                + "name=\"format-detection\"><title>Nuevo mensaje</title><!--[if (mso 16)]><style type=\"text/css\"> "
                + "a {text-decoration: none;} </style><![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }"
                + "</style><![endif]--><!--[if gte mso 9]><xml> <o:OfficeDocumentSettings> <o:AllowPNG></o:AllowPNG> "
                + "<o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml><![endif]--><style type=\"text/css\">"
                + "#outlook a { padding:0;}.es-button { mso-style-priority:100!important; text-decoration:none!important;}a[x-apple-data-detectors] "
                + "{ color:inherit!important; text-decoration:none!important; font-size:inherit!important; font-family:inherit!important; "
                + "font-weight:inherit!important; line-height:inherit!important;}.es-desk-hidden { display:none; float:left; overflow:hidden; "
                + "width:0; max-height:0; line-height:0; mso-hide:all;}@media only screen and (max-width:600px) {p, ul li, ol li, "
                + "a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a "
                + "{ line-height:120% } h1 { font-size:36px!important; text-align:center } "
                + "h2 { font-size:28px!important; text-align:center } h3 { font-size:20px!important; text-align:center } .es-header-body h1 "
                + "a, .es-content-body h1 a, .es-footer-body h1 a { font-size:36px!important; text-align:center } "
                + ".es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:28px!important; text-align:center } "
                + ".es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:center } "
                + ".es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, "
                + ".es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, "
                + ".es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, "
                + ".es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, "
                + ".es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } "
                + ".es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, "
                + ".es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, "
                + ".es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l "
                + "img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button "
                + "{ font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } "
                + ".es-content table, .es-header table, .es-footer table, .es-content, .es-footer, "
                + ".es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } "
                + ".adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } "
                + ".es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } "
                + ".es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, "
                + "td.es-desk-hidden, table.es"
                + "-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; "
                + "max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important }"
                + " table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } "
                + ".es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } "
                + "table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 "
                + "{ padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } "
                + ".es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } "
                + ".es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r "
                + "{ padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } "
                + ".es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r "
                + "{ padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } "
                + ".es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l "
                + "{ padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } "
                + ".es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } "
                + ".es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } "
                + ".es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } "
                + ".es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } "
                + ".es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } "
                + ".es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } "
                + ".es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } "
                + ".es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; "
                + "max-height:inherit!important } }</style></head>\n" +
            "<body style=\"width:100%;font-family:georgia, times, 'times new roman', serif;-webkit-text-size-adjust:100%;"
                + "-ms-text-size-adjust:100%;padding:0;Margin:0\"><div class=\"es-wrapper-color\" style=\"background-color:#146C94\">"
                + "<!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> "
                + "<v:fill type=\"tile\" color=\"#146C94\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill> "
                + "</v:background><![endif]--><table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;"
                + "height:100%;background-repeat:repeat;background-position:center top;background-color:#146C94\">"
                + "<tr><td valign=\"top\" style=\"padding:0;Margin:0\"><table cellpadding=\"0\" cellspacing=\"0\" "
                + "class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:"
                + "collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:"
                + "repeat;background-position:center top\"><tr><td align=\"center\" style=\"padding:0;Margin:0\">"
                + "<table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;"
                + "background-color:#FFFFFF;width:600px\"><tr><td align=\"left\" style=\"Margin:0;padding-top:15px;"
                + "padding-bottom:20px;padding-left:20px;padding-right:20px\"><table cellpadding=\"0\" "
                + "cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;"
                + "border-collapse:collapse;border-spacing:0px\"><tr><td class=\"es-m-p0r es-m-p20b\" "
                + "valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">"
                + "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt"
                + ";mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"><tr><td align=\"center\" style=\"padding:0;Margin:0\">"
                + "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, "
                + "'helvetica neue', helvetica, sans-serif;line-height:51px;color:#333333;font-size:34px\">Bienvenido a Linealizate</p>\n" +
            "</td></tr></table></td></tr></table></td></tr></table></td>\n" +
            "</tr></table><table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed "
                + "!important;width:100%\"><tr><td align=\"center\" style=\"padding:0;Margin:0\">"
                + "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse"
                + ";border-spacing:0px;background-color:#ffffff;width:600px\" cellspacing=\"0\" cellpadding=\"0\" "
                + "bgcolor=\"#ffffff\" align=\"center\"><tr><td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-top:20px;"
                + "padding-left:20px;padding-right:20px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"
                + "<tr><td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">"
                + "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"
                + "<tr><td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" "
                + "src=\"https://kmgkqf.stripocdn.email/content/guids/CABINET_2bdf44fb45870fcaeed9e5cec697832b1d23995f9b7d2ccdacb7672cb162a85a/images/undraw_people_re_8spw.png\" "
                + "alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"300\"></td>\n" +
            "</tr><tr><td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\">"
                + "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, "
                + "'helvetica neue', helvetica, sans-serif;line-height:27px;color:#333333;font-size:18px\">"
                + "Tu maestra te ha registrado en Linealizate es una plataforma de contenido didáctico con el fin de brinda un apoyo para la materia de Algebra Lineal.</p>"
                + "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:georgia, times, "
                + "'times new roman', serif;line-height:24px;color:#333333;font-size:16px\"><br></p>\n" +
            "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, "
                + "'helvetica neue', helvetica, sans-serif;line-height:24px;color:#333333;font-size:16px\">"
                + "<span style=\"font-size:18px\">Ingresa con tu Boleta y tu contraseña.</span></p>"
                + "<p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:27px;color:#333333;font-size:18px\">"
                + "</p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:27px;color:#333333;font-size:18px\">"
                + "La Boleta Es tu contraseña</p></td></tr></table></td></tr></table></td></tr></table></td>\n" +
            "</tr></table><table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed "
                + "!important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">"
                + "<tr><td align=\"center\" style=\"padding:0;Margin:0\"><table bgcolor=\"#3c2c4c\" class=\"es-footer-body\" align=\"center\" "
                + "cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;"
                + "border-spacing:0px;background-color:#FFFFFF;width:600px\"><tr><td align=\"left\" style=\"padding:20px;Margin:0\">"
                + "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"
                + "<tr><td align=\"left\" style=\"padding:0;Margin:0;width:560px\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" "
                + "style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">"
                + "<tr><td align=\"center\" style=\"padding:0;Margin:0\"><span class=\"es-button-border\" "
                + "style=\"border-style:solid;border-color:#2CB543;background:#146c94;"
                + "border-width:0px 0px 2px 0px;display:inline-block;border-radius:30px;width:auto\">"
                + "<a href=\""+Link+"\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 "
                + "!important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;"
                + "mso-line-height-rule:exactly;color:#FFFFFF;font-size:18px;display:"
                + "inline-block;background:#146c94;border-radius:30px;font-family:arial, "
                + "'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;"
                + "line-height:22px;width:auto;text-align:center;padding:10px 20px 10px 20px;"
                + "mso-padding-alt:0;mso-border-alt:10px solid #146C94\">Ingresar</a>"
                + "</span></td>"
                + "</tr></table></td></tr></table></td></tr></table></td></tr></table>"
                + "</td></tr></table></div></body></html>";
        return correo;
    }
    
    public static String CorreoRecuperarContra(String token){
        String linkauth = Link+"/Recuperar.jsp?token="+token;
        String correo="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:georgia, times, 'times new roman', serif\">\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
            "<meta name=\"x-apple-disable-message-reformatting\">\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "<meta content=\"telephone=no\" name=\"format-detection\">\n" +
            "<title>Nuevo mensaje</title><!--[if (mso 16)]>\n" +
            "<style type=\"text/css\">\n" +
            "a {text-decoration: none;}\n" +
            "</style>\n" +
            "<![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\n" +
            "<xml>\n" +
            "<o:OfficeDocumentSettings>\n" +
            "<o:AllowPNG></o:AllowPNG>\n" +
            "<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
            "</o:OfficeDocumentSettings>\n" +
            "</xml>\n" +
            "<![endif]-->\n" +
            "<style type=\"text/css\">\n" +
            "#outlook a {\n" +
            "padding:0;\n" +
            "}\n" +
            ".es-button {\n" +
            "mso-style-priority:100!important;\n" +
            "text-decoration:none!important;\n" +
            "}\n" +
            "a[x-apple-data-detectors] {\n" +
            "color:inherit!important;\n" +
            "text-decoration:none!important;\n" +
            "font-size:inherit!important;\n" +
            "font-family:inherit!important;\n" +
            "font-weight:inherit!important;\n" +
            "line-height:inherit!important;\n" +
            "}\n" +
            ".es-desk-hidden {\n" +
            "display:none;\n" +
            "float:left;\n" +
            "overflow:hidden;\n" +
            "width:0;\n" +
            "max-height:0;\n" +
            "line-height:0;\n" +
            "mso-hide:all;\n" +
            "}\n" +
            "@media only screen and (max-width:600px) {p, ul li, ol li, "
                + "a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } "
                + "h1 { font-size:36px!important; text-align:center } h2 { font-size:28px!important;"
                + " text-align:center } h3 { font-size:20px!important; text-align:center } "
                + ".es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 "
                + "a { font-size:36px!important; text-align:center } .es-header-body h2 a, "
                + ".es-content-body h2 a, .es-footer-body h2 a { font-size:28px!important; text-align:center } "
                + ".es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 "
                + "a { font-size:20px!important; text-align:center } .es-menu td a { font-size:14px!important } "
                + ".es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body "
                + "a { font-size:14px!important } .es-content-body p, .es-content-body ul li, "
                + ".es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, "
                + ".es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } "
                + ".es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock "
                + "a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } "
                + ".es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } "
                + ".es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } "
                + ".es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } "
                + ".es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } "
                + ".es-button-border { display:inline-block!important } a.es-button, "
                + "button.es-button { font-size:18px!important; display:inline-block!important } "
                + ".es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, "
                + ".es-header table, .es-footer table, .es-content, .es-footer, "
                + ".es-header { width:100%!important; max-width:600px!important } "
                + ".es-adapt-td { display:block!important; width:100%!important } "
                + ".adapt-img { width:100%!important; height:auto!important } "
                + ".es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } "
                + ".es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } "
                + ".es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } "
                + ".es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, "
                + "td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; "
                + "float:none!important; max-height:inherit!important; line-height:inherit!important } "
                + "tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden "
                + "{ display:table!important } td.es-desk-menu-hidden { display:table-cell!important } "
                + ".es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html "
                + "table { width:auto!important } table.es-social { display:inline-block!important } "
                + "table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } "
                + ".es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } "
                + ".es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } "
                + ".es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } "
                + ".es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } "
                + ".es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } "
                + ".es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } "
                + ".es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } "
                + ".es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } "
                + ".es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } "
                + ".es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } "
                + ".es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } "
                + ".es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } "
                + ".es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } "
                + ".es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } "
                + ".es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } "
                + ".es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } "
                + ".es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } "
                + ".es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } "
                + ".es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } "
                + ".es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important;"
                + " max-height:inherit!important } }\n" +
            "</style>\n" +
            "</head>\n" +
            "<body style=\"width:100%;font-family:georgia, times, 'times new roman', serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
            "<div class=\"es-wrapper-color\" style=\"background-color:#146C94\"><!--[if gte mso 9]>\n" +
            "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
            "<v:fill type=\"tile\" color=\"#146C94\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill>\n" +
            "</v:background>\n" +
            "<![endif]-->\n" +
            "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#146C94\">\n" +
            "<tr>\n" +
            "<td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
            "<table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
            "<tr>\n" +
            "<td align=\"left\" style=\"Margin:0;padding-top:15px;padding-bottom:20px;padding-left:20px;padding-right:20px\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
            "<tr>\n" +
            "<td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:51px;color:#333333;font-size:34px\">Recupera tu Contraseña</p></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
            "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
            "<tr>\n" +
            "<td align=\"left\" style=\"Margin:0;padding-bottom:10px;padding-top:20px;padding-left:20px;padding-right:20px\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
            "<tr>\n" +
            "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://kmgkqf.stripocdn.email/content/guids/CABINET_2bdf44fb45870fcaeed9e5cec697832b1d23995f9b7d2ccdacb7672cb162a85a/images/undraw_my_password_re_ydq7.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"300\"></td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:27px;color:#333333;font-size:18px\">Ingresa en el link para restablecer tu contraseña</p></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0\">\n" +
            "<table bgcolor=\"#3c2c4c\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
            "<tr>\n" +
            "<td align=\"left\" style=\"padding:20px;Margin:0\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
            "<tr>\n" +
            "<td align=\"left\" style=\"padding:0;Margin:0;width:560px\">\n" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
            "<tr>\n" +
            "<td align=\"center\" style=\"padding:0;Margin:0\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#146c94;border-width:0px 0px 2px 0px;"
                + "display:inline-block;border-radius:30px;width:auto\">"
                + "<a href=\""+linkauth+"\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 "
                + "!important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:18px;display:inline-block;background:#146c94;border-radius:30px;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;padding:10px 20px 10px 20px;mso-padding-alt:0;mso-border-alt:10px solid #146C94\">Recuperar</a></span></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
        
        return correo;
    }
}
