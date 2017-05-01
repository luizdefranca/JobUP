using AutoMapper;
using JOB.DATA;
using JOB.WEB.Models;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class ServicoPublicoController : ApiController
    {
        private Contexto ctx = new Contexto();

        public HttpResponseMessage Get()
        {
            var lstDominio = ctx.Servico.Where(w => w.PUBLICO).ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.ESPECIALIDADES = ctx.Especialidade.ToList();
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE)
                    .DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade
                        .FirstOrDefault(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE)
                        .DESCRICAO;
                }
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }
    }
}