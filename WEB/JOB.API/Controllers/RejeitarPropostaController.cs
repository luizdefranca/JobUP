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
    /// API de rejeição de propostas (visão cliente)
    /// </summary>
    public class RejeitarPropostaController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// Cliente rejeita a proposta oferecida pelo profissional
        /// </summary>
        /// <param name="id">id do serviço</param>
        /// <param name="ID_USUARIO">id do profissional (dono da proposta)</param>
        /// <returns></returns>
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Get(Guid id, Guid ID_USUARIO)
        {
            var domain = ctx.Proposta.First(f => f.ID_SERVICO == id & f.ID_USUARIO == ID_USUARIO);

            domain.RejeitarProposta();
            ctx.Entry(domain).State = EntityState.Modified;
            ctx.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.OK);
        }
    }
}