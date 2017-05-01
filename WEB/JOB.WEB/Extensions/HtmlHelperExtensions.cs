using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Extensions
{
    public static class HtmlHelperExtensions
    {
        public static MvcHtmlString CustomValidationSummary(this HtmlHelper htmlHelper, bool excludePropertyErrors = false, bool closeable = true)
        {
            IList<string> errorList = new List<string>();

            if (excludePropertyErrors)
            {
                ModelState ms;
                htmlHelper.ViewData.ModelState.TryGetValue(htmlHelper.ViewData.TemplateInfo.HtmlFieldPrefix, out ms);
                if (ms != null)
                    errorList = ms.Errors.Select(e => e.ErrorMessage).ToList();
            }
            else
            {
                var errors = htmlHelper.ViewContext.ViewData.ModelState.SelectMany(state => state.Value.Errors.Select(error => error.ErrorMessage));
                errorList = errors as IList<string> ?? errors.ToList();
            }
            var errorCount = errorList.Count();

            if (errorCount == 0)
            {
                return new MvcHtmlString(string.Empty);
            }

            var div = new TagBuilder("div");
            div.AddCssClass("inspinia-notify"); //Add Inspinia notification style
            div.AddCssClass("alert");
            div.AddCssClass("alert-danger");
            div.Attributes["data-valmsg-summary=true"] = "true";
            string message;

            if (errorCount == 1)
            {
                message = errorList.First();
            }
            else
            {
                message = "Por favor, corrija os itens abaixo e tente novamente.";
                div.AddCssClass("alert-block");
            }
            if (closeable)
            {
                var button = new TagBuilder("button");
                button.AddCssClass("close");
                button.MergeAttribute("type", "button");
                button.MergeAttribute("data-dismiss", "alert");
                button.InnerHtml = "&times;";
                div.InnerHtml += button.ToString();
            }
            div.InnerHtml += string.Format("<strong>Central de Validação.</strong> {0}", message);
            if (errorCount > 1)
            {
                var ul = new TagBuilder("ul");
                foreach (var error in errorList)
                {
                    var li = new TagBuilder("li");
                    li.AddCssClass("text-error");
                    li.SetInnerText(error);
                    ul.InnerHtml += li.ToString();
                }
                div.InnerHtml += ul.ToString();
            }
            return new MvcHtmlString(div.ToString());
        }
    }
}