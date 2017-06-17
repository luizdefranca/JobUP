using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de propostas de servicos (resposta do profissional a uma oferta de servico)
    /// </summary>
    public class PropostaController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// recupera todas as propostas de um determinado usuario profissional
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <returns></returns>
        [ResponseType(typeof(List<PropostaViewModel>))]
        public HttpResponseMessage Get(Guid idUsuario)
        {
            var lstDominio = ctx.Proposta.Where(w => w.ID_USUARIO == idUsuario).ToList();

            var lstModel = Mapper.Map<List<PropostaViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.DS_TITULO = ctx.Servico.First(f => f.ID_SERVICO == model.ID_SERVICO).DS_TITULO;
                model.DS_OBSERVACOES = ctx.Servico.First(f => f.ID_SERVICO == model.ID_SERVICO).DS_OBSERVACOES;
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }

        /// <summary>
        /// Profissional aceita uma oferta de servico e gera uma proposta
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(PROPOSTA_SERVICO obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                var domain = ctx.Oferta.First(f => f.ID_SERVICO == obj.ID_SERVICO);

                domain.AceitarOferta();
                ctx.Entry(domain).State = EntityState.Modified;

                var proposta = new PROPOSTA_SERVICO(obj.ID_SERVICO, obj.ID_USUARIO, obj.VL_PROPOSTA, obj.JUSTIFICATIVA, obj.DURACAO_SERVICO, obj.VALOR_DURACAO_SERVICO);
                ctx.Proposta.Add(proposta);

                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }
    }
}