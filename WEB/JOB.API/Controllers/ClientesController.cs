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
    /// <summary>
    /// API de usuario clientes
    /// </summary>
    public class ClientesController : ApiController
    {
        /// <summary>
        /// Aprova um determinado usuario
        /// </summary>
        /// <param name="id">id do usuario</param>
        /// <returns></returns>
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