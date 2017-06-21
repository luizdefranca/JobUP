using JOB.DATA;
using JOB.HELPERS.Validation;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de desativar usuário
    /// </summary>
    public class DesativarController : System.Web.Http.ApiController
    {
        /// <summary>
        /// Desativa um determinado usuário
        /// </summary>
        /// <param name="id">id do usuário</param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
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