using JOB.DATA;
using JOB.HELPERS.Validation;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class DesativarController : System.Web.Http.ApiController
    {
        public HttpResponseMessage Get(Guid id)
        {
            using (Contexto ctx = new Contexto())
            {
                try
                {
                    var usuario = ctx.Usuario.First(w => w.ID_USUARIO == id);

                    usuario.Desativar();
                    ctx.Entry(usuario).State = EntityState.Modified;
                    ctx.SaveChanges();

                    return Request.CreateResponse(HttpStatusCode.OK, "Success");
                }
                catch (Exception ex)
                {
                    return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
                }
            }
        }
    }
}