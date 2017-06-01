using JOB.DATA;
<<<<<<< HEAD
=======
using JOB.HELPERS.Validation;
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class ClientesController : ApiController
    {
        public HttpResponseMessage Get(Guid id)
        {
            using (Contexto ctx = new Contexto())
            {
                try
                {
                    var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

                    domain.Aprovar();
                    ctx.Entry(domain).State = EntityState.Modified;
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