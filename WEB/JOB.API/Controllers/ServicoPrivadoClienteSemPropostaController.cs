using AutoMapper;
using JOB.DATA;
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
    public class ServicoPrivadoClienteSemPropostaController : ApiController
    {
        private readonly Contexto ctx = new Contexto();

        public HttpResponseMessage Get(Guid idUsuarioCliente)
        {
            //TODO: nao tem que ser privado. tem q ser qualquer um
            var domain = ctx.Servico.Include(i => i.PROPOSTAS).Include(i => i.OFERTAS).Where(w => w.ID_USUARIO == idUsuarioCliente & w.PUBLICO == false & !w.PROPOSTAS.Any()).ToList();

            var lstModel = Mapper.Map<List<ServicoViewModel_api>>(domain);

            //ClaimsIdentity claimIdenties = HttpContext.User.Identity as ClaimsIdentity;

            //ViewBag.POSSUI_FACE = claimIdenties.FindFirst("FacebookAccessToken") != null;

            foreach (var model in lstModel)
            {
                model.ID_PROFISSIONAL = domain.First(f => f.ID_SERVICO == model.ID_SERVICO).OFERTAS.First().ID_USUARIO;
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                if (model.ID_SUB_ESPECIALIDADE != null && model.ID_SUB_ESPECIALIDADE != 0)
                {
                    model.DESC_SUB_ESPECIALIDADE = ctx.SubEspecialidade.First(f => f.ID_SUB_ESPECIALIDADE == model.ID_SUB_ESPECIALIDADE).DESCRICAO;
                }
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }
    }
}