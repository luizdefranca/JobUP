using JOB.DATA;
using JOB.HELPERS.Validation;
using JOB.WEB.Helper;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de aprovação de usuário (cliente ou profissional)
    /// </summary>
    public class AprovacaoUsuarioController : ApiController
    {
        /// <summary>
        /// Aprova um determinado usuário
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
                    var domain = ctx.Usuario.First(w => w.ID_USUARIO == id);

                    MoedaHelper.Movimentar(ctx, id, 1000, "CADASTRO NO SISTEMA");

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