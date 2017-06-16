using JOB.DATA;
using JOB.DATA.Domain;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de rejeicao de servico
    /// </summary>
    public class AceitarServicoController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// Rejeita um determinado servico
        /// </summary>
        /// <param name="id">id do servico</param>
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
        public HttpResponseMessage Get(Guid ID_SERVICO, Guid ID_USUARIO)
        {
            var domain = ctx.Oferta.First(f => f.ID_SERVICO == ID_SERVICO);

            domain.AceitarOferta();
            ctx.Entry(domain).State = EntityState.Modified;

            var proposta = new PROPOSTA_SERVICO(ID_SERVICO, ID_USUARIO, obj.VL_PROPOSTA, obj.JUSTIFICATIVA, obj.DURACAO_SERVICO, obj.VALOR_DURACAO_SERVICO);
            ctx.Proposta.Add(proposta);

            ctx.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.OK);
        }
    }
}