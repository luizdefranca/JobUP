using AutoMapper;
using JOB.DATA;
using JOB.WEB.Helper;
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
    /// API de servico privado (feito exclusivamente para um determinado profissional)
    /// </summary>
    public class ServicoPrivadoClienteController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// recupera todos os servicos privados que possuem propostas de um determinado usuario cliente (juntamente com as propostas)
        /// </summary>
        /// <param name="idUsuarioCliente">id do usuario cliente</param>
        /// <returns></returns>
        [ResponseType(typeof(List<ServicoViewModel_api>))]
        public HttpResponseMessage Get(Guid idUsuarioCliente)
        {
            var lstDominio = ctx.Servico
                .Include(i => i.PROPOSTAS)
                .Include(i => i.OFERTAS)
                .Where(w => w.ID_USUARIO == idUsuarioCliente & w.PUBLICO == false & w.PROPOSTAS.Any())
                .ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel_api>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.ID_PROFISSIONAL = lstDominio.First(f => f.ID_SERVICO == model.ID_SERVICO).OFERTAS.First().ID_USUARIO;
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null && model.ID_SUB_ESPECIALIDADE != 0)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;
                }

                model.TEMPO_SERVICO_DESC = EnumHelper.GetName(model.TEMPO_SERVICO);
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }
    }
}