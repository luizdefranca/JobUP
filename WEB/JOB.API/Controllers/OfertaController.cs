using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace JOB.API.Controllers
{
    /// <summary>
    /// API das ofertas de serviços
    /// </summary>
    public class OfertaController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// Lista as ofertas de um determinado profissional
        /// </summary>
        /// <param name="idUsuario">id do usuário</param>
        /// <returns></returns>
        [ResponseType(typeof(List<OfertaViewModel>))]
        public HttpResponseMessage Get(Guid idUsuario)
        {
            var lstDominio = ctx.Oferta.Where(w => w.ID_USUARIO == idUsuario).ToList();

            var lstModel = Mapper.Map<List<OfertaViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.DS_TITULO = ctx.Servico.First(f => f.ID_SERVICO == model.ID_SERVICO).DS_TITULO;
                model.DS_OBSERVACOES = ctx.Servico.First(f => f.ID_SERVICO == model.ID_SERVICO).DS_OBSERVACOES;
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }
    }
}