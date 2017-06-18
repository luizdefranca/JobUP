using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
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
    public class ServicoPrivadoController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        /// <summary>
        /// recupera todos os servicos privados que possuem propostas de um determinado usuario profissional
        /// </summary>
        /// <param name="idUsuarioProfissional">id do usuario profissional</param>
        /// <returns></returns>
        [ResponseType(typeof(List<ServicoViewModel_api>))]
        public HttpResponseMessage Get(Guid idUsuarioProfissional)
        {
            var lstDominio = ctx.Servico.Include(i => i.PROPOSTAS).Include(i => i.OFERTAS).Where(w => w.PUBLICO == false & w.OFERTAS.Any(a => a.ID_USUARIO == idUsuarioProfissional)).ToList();

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

        /// <summary>
        /// insere um novo servico privado (direcionado diretamente para um profissional)
        /// </summary>
        /// <param name="obj">classe ServicoViewModel_api</param>
        /// <returns></returns>
        [HttpPost]
        [ResponseType(typeof(HttpStatusCode))]
        public HttpResponseMessage Post(ServicoViewModel_api obj)
        {
            try
            {
                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                var domain = new SERVICO(obj.ID_SERVICO, obj.ID_USUARIO, obj.ID_ESPECIALIDADE, obj.ID_SUB_ESPECIALIDADE, false, obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO, obj.TEMPO_SERVICO);
                ctx.Servico.Add(domain);

                var objOferta = new OFERTA_SERVICO(obj.ID_SERVICO, obj.ID_PROFISSIONAL);
                ctx.Oferta.Add(objOferta);

                MoedaHelper.Movimentar(ctx, obj.ID_USUARIO, -100, "SERVIÇO PRIVADO OFERTADO");

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