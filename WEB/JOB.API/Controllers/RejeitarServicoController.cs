using JOB.DATA;
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
    /// API de rejeição da oferta de serviço (visão profissional)
    /// </summary>
    public class RejeitarServicoController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// Profissional rejeita a oferta de serviço oferecida pelo cliente
        /// </summary>
        /// <param name="id">id do servico</param>
        /// <param name="JUSTIFICATIVA">justificativa da rejeiçao</param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Get(Guid id, string JUSTIFICATIVA)
        {
            var domain = ctx.Oferta.First(f => f.ID_SERVICO == id);

            domain.RejeitarOferta(JUSTIFICATIVA);
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.OK);
        }
    }
}