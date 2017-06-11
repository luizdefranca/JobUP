using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API de propostas de servicos (resposta do profissional)
    /// </summary>
    public class PropostaController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// recupera todas as propostas de um determinado usuario profissional
        /// </summary>
        /// <param name="idUsuario">id do usuario</param>
        /// <returns>classe PropostaViewModel</returns>
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
    }
}