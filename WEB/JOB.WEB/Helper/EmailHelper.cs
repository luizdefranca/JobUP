using NLog;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Net.Mime;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace AgendaCirurgicaBeta.Helper
{
    public class Email
    {
        private static Logger logger = LogManager.GetCurrentClassLogger();

        public Email(string ServidorSMTP, int PortaSMTP, bool SSL, bool AutenticacaoEmail = true, string UserCredencial = "", string PassCredencial = "", bool AutRequerEmailCompleto = true)
        {
            if (string.IsNullOrEmpty(ServidorSMTP)) throw new ArgumentException("ServidorSMTP");
            if (PortaSMTP <= 0) throw new ArgumentException("PortaSMTP");
            if (AutenticacaoEmail && (string.IsNullOrEmpty(UserCredencial) | string.IsNullOrEmpty(PassCredencial))) throw new ArgumentException("UserCredencial|PassCredencial");

            this.ServidorSMTP = ServidorSMTP;
            this.PortaSMTP = PortaSMTP;
            this.SSL = SSL;
            this.AutenticacaoEmail = AutenticacaoEmail;
            this.UserCredencial = UserCredencial;
            this.PassCredencial = PassCredencial;
            this.AutRequerEmailCompleto = AutRequerEmailCompleto;
        }

        public string ServidorSMTP { get; set; }
        public int PortaSMTP { get; set; }
        public bool SSL { get; set; }
        public bool AutenticacaoEmail { get; set; }
        public string UserCredencial { get; set; }
        public string PassCredencial { get; set; }
        public bool AutRequerEmailCompleto { get; set; }

        /// <summary>
        ///     Envia um email via SMTP para uma coleção de remetentes usando o recurso de Parallel (thread)
        /// </summary>
        /// <param name="mailMessages"></param>
        public async Task SendEmailAsync(List<Mail> mailMessages)
        {
            foreach (var mail in mailMessages)
            {
                await SendEmailAsync(mail);
            }
        }

        /// <summary>
        ///     Envia um email via SMTP para um remetente específico
        /// </summary>
        /// <param name="mailMessage"></param>
        /// <returns></returns>
        public async Task SendEmailAsync(Mail mailMessage)
        {
            var smtp = new SmtpClient(ServidorSMTP, PortaSMTP)
            {
                EnableSsl = SSL,
                DeliveryMethod = SmtpDeliveryMethod.Network,
                UseDefaultCredentials = false,
                Credentials = new NetworkCredential(AutRequerEmailCompleto ? UserCredencial : UserCredencial.Split('@')[0], PassCredencial)
            };

            if (!AutenticacaoEmail)
            {
                smtp.UseDefaultCredentials = true;
                smtp.Credentials = null;
            }

            using (var mail = new MailMessage())
            {
                mail.From = new MailAddress(UserCredencial);

                for (var i = 0; i < mailMessage.emailPara.Length; i++)
                {
                    mail.To.Add(new MailAddress(mailMessage.emailPara[i]));
                }

                mail.Subject = mailMessage.assuntoEmail;
                mail.Body = mailMessage.msgCorpoEmail;
                mail.IsBodyHtml = true;

                if (mailMessage.streamImagemInline != null)
                    mail.Attachments.Add(RetornaAnexoInline(mailMessage.streamImagemInline));

                if (mailMessage.anexos != null && mailMessage.anexos.Any())
                {
                    foreach (var anexo in mailMessage.anexos)
                    {
                        mail.Attachments.Add(RetornaAnexo(anexo));
                    }
                }

                try
                {
                    await smtp.SendMailAsync(mail);
                }
                catch (SmtpException ex)
                {
                    logger.Error(ex);
                    throw new Exception(TratarExcecaoSmtp(ex));
                }
            }
        }

        public bool TestSendEmail(Mail mailMessage)
        {
            var smtp = new SmtpClient(ServidorSMTP, PortaSMTP)
            {
                EnableSsl = SSL,
                DeliveryMethod = SmtpDeliveryMethod.Network,
                UseDefaultCredentials = false,
                Credentials = new NetworkCredential(AutRequerEmailCompleto ? UserCredencial : UserCredencial.Split('@')[0], PassCredencial)
            };

            if (!AutenticacaoEmail)
            {
                smtp.UseDefaultCredentials = true;
                smtp.Credentials = null;
            }

            using (var mail = new MailMessage())
            {
                mail.From = new MailAddress(UserCredencial);

                for (var i = 0; i < mailMessage.emailPara.Length; i++)
                {
                    mail.To.Add(new MailAddress(mailMessage.emailPara[i]));
                }

                mail.Subject = mailMessage.assuntoEmail;
                mail.Body = mailMessage.msgCorpoEmail;
                mail.IsBodyHtml = true;

                if (mailMessage.streamImagemInline != null)
                    mail.Attachments.Add(RetornaAnexoInline(mailMessage.streamImagemInline));

                if (mailMessage.anexos != null && mailMessage.anexos.Any())
                {
                    foreach (var anexo in mailMessage.anexos)
                    {
                        mail.Attachments.Add(RetornaAnexo(anexo));
                    }
                }

                try
                {
                    smtp.Send(mail);
                }
                catch (SmtpException ex)
                {
                    throw new Exception(TratarExcecaoSmtp(ex));
                }
            }

            return true;
        }

        private string TratarExcecaoSmtp(SmtpException ex)
        {
            //if (ex.StatusCode == SmtpStatusCode.BadCommandSequence)
            //{
            //    return BI.Helpers.Resource.Traducoes.BadCommandSequence;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.CannotVerifyUserWillAttemptDelivery)
            //{
            //    return BI.Helpers.Resource.Traducoes.CannotVerifyUserWillAttemptDelivery;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.ClientNotPermitted)
            //{
            //    return BI.Helpers.Resource.Traducoes.ClientNotPermitted;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.CommandNotImplemented)
            //{
            //    return BI.Helpers.Resource.Traducoes.CommandNotImplemented;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.CommandParameterNotImplemented)
            //{
            //    return BI.Helpers.Resource.Traducoes.CommandParameterNotImplemented;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.CommandUnrecognized)
            //{
            //    return BI.Helpers.Resource.Traducoes.CommandUnrecognized;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.ExceededStorageAllocation)
            //{
            //    return BI.Helpers.Resource.Traducoes.ExceededStorageAllocation;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.GeneralFailure)
            //{
            //    return BI.Helpers.Resource.Traducoes.GeneralFailure;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.HelpMessage)
            //{
            //    return BI.Helpers.Resource.Traducoes.HelpMessage;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.InsufficientStorage)
            //{
            //    return BI.Helpers.Resource.Traducoes.InsufficientStorage;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.LocalErrorInProcessing)
            //{
            //    return BI.Helpers.Resource.Traducoes.LocalErrorInProcessing;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.MailboxBusy)
            //{
            //    return BI.Helpers.Resource.Traducoes.MailboxBusy;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.MailboxNameNotAllowed)
            //{
            //    return BI.Helpers.Resource.Traducoes.MailboxNameNotAllowed;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.MailboxUnavailable)
            //{
            //    return BI.Helpers.Resource.Traducoes.MailboxUnavailable;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.MustIssueStartTlsFirst)
            //{
            //    return BI.Helpers.Resource.Traducoes.MustIssueStartTlsFirst;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.ServiceClosingTransmissionChannel)
            //{
            //    return BI.Helpers.Resource.Traducoes.ServiceClosingTransmissionChannel;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.ServiceNotAvailable)
            //{
            //    return BI.Helpers.Resource.Traducoes.ServiceNotAvailable;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.SyntaxError)
            //{
            //    return BI.Helpers.Resource.Traducoes.SyntaxError;
            //}
            //else if (ex.StatusCode == SmtpStatusCode.TransactionFailed)
            //{
            //    return BI.Helpers.Resource.Traducoes.TransactionFailed;
            //}
            //else
            //{
            return ex.Message;
            //}
        }

        private Attachment RetornaAnexoInline(Stream fileStream)
        {
            if (fileStream == null) throw new NullReferenceException("stream do anexo não disponivel!");

            var att = new Attachment(fileStream, "logo.jpeg")
            {
                ContentType = new ContentType() { MediaType = MediaTypeNames.Image.Jpeg },
                ContentId = "BackgroundImage"
            };

            att.ContentType.Name = "BackgroundImage";
            att.ContentDisposition.Inline = true;
            att.ContentDisposition.DispositionType = DispositionTypeNames.Inline;
            att.TransferEncoding = TransferEncoding.Base64;

            return att;
        }

        private Attachment RetornaAnexo(Anexo anexo)
        {
            if (anexo.stream == null) throw new NullReferenceException("stream do anexo não disponivel!");

            var att = new Attachment(anexo.stream, anexo.fileName, anexo.type);

            att.ContentDisposition.Inline = false;
            att.ContentDisposition.DispositionType = DispositionTypeNames.Attachment;
            att.TransferEncoding = TransferEncoding.Base64;

            return att;
        }
    }

    public static class EmailUtilities
    {
        public static bool IsValidEmail(string strIn)
        {
            if (string.IsNullOrEmpty(strIn)) return false;

            // Use IdnMapping class to convert Unicode domain names.
            try
            {
                strIn = Regex.Replace(strIn, @"(@)(.+)$", DomainMapper);
            }
            catch (Exception)
            {
                return false;
            }

            // Return true if strIn is in valid e-mail format.
            return Regex.IsMatch(strIn,
                @"^(?("")(""[^""]+?""@)|(([0-9a-z]((\.(?!\.))|[-!#\$%&'\*\+/=\?\^`\{\}\|~\w])*)(?<=[0-9a-z])@))" +
                @"(?(\[)(\[(\d{1,3}\.){3}\d{1,3}\])|(([0-9a-z][-\w]*[0-9a-z]*\.)+[a-z0-9]{2,17}))$",
                RegexOptions.IgnoreCase);
        }

        private static string DomainMapper(Match match)
        {
            // IdnMapping class with default property values.
            var idn = new IdnMapping();

            var domainName = match.Groups[2].Value;
            domainName = idn.GetAscii(domainName);

            return match.Groups[1].Value + domainName;
        }
    }

    /// <summary>
    /// Classe que representa o email a ser enviado (formato aceito é apenas em html)
    /// </summary>
    public class Mail
    {
        public string assuntoEmail { get; private set; }
        public string[] emailPara { get; private set; }
        public string msgCorpoEmail { get; private set; }

        public Stream streamImagemInline { get; private set; }
        public List<Anexo> anexos { get; private set; }

        public Mail(string assuntoEmail, string[] emailPara, string msgCorpoEmail)
        {
            anexos = new List<Anexo>();

            this.assuntoEmail = assuntoEmail;
            this.emailPara = emailPara;
            this.msgCorpoEmail = msgCorpoEmail;
        }

        /// <summary>
        /// Incorpora uma imagem no email, no formato: src='cid:BackgroundImage'
        /// </summary>
        /// <param name="stream"></param>
        public void AdicionarImagemInline(Stream stream)
        {
            this.streamImagemInline = stream;
        }

        /// <summary>
        /// Adiciona um anexo ao email
        /// </summary>
        /// <param name="anexo"></param>
        public void AdicionarAnexo(Anexo anexo)
        {
            this.anexos.Add(anexo);
        }
    }

    public class Anexo
    {
        public Stream stream { get; private set; }
        public string type { get; private set; }
        public string fileName { get; private set; }

        /// <summary>
        /// Gera um novo tipo de anexo
        /// </summary>
        /// <param name="stream"></param>
        /// <param name="type">MediaTypeNames.Image ou MediaTypeNames.Application</param>
        /// <param name="fileName"></param>
        public Anexo(Stream stream, string type, string fileName)
        {
            this.stream = stream;
            this.type = type;
            this.fileName = fileName;
        }
    }
}