using System.Web.Mvc;

namespace JOB.WEB.CustomFilters
{
    public class ClearModelErrorsAttribute : ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext filterContext)
        {
            ModelStateDictionary msd = filterContext.Controller.ViewData.ModelState;
            foreach (var item in msd.Values)
            {
                item.Errors.Clear();
            }
        }
    }
}