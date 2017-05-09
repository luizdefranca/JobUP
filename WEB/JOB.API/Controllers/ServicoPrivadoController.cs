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

namespace JOB.API.Controllers
{
    public class ServicoPrivadoController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        public HttpResponseMessage Get(Guid idUsuarioProfissional)
        {
            //recupera apenas os serviços privados que tenha uma oferta para um usuário profissional especifico
            var lstDominio = ctx.Servico.Include(i => i.OFERTAS).Where(w => w.PUBLICO == false & w.OFERTAS.Any(a => a.ID_USUARIO == idUsuarioProfissional)).ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel_api>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;
                }

                model.TEMPO_SERVICO_DESC = EnumHelper.GetName(model.TEMPO_SERVICO);
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }
    }
}