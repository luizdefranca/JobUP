using AutoMapper;
using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using JOB.WEB.Models;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class PerfilProfissionalController : ApiController
    {
        private Contexto ctx = new Contexto();

        public HttpResponseMessage Get(int idEspecialidade)
        {
            //var lstDominio = ctx.PerfilProfissional.Where(f => f.APROVADO == true).ToList();
            try
            {
                var lstDominio = ctx.PerfilProfissional.Where(f => f.ID_ESPECIALIDADE == idEspecialidade).ToList();

                var lstModel = Mapper.Map<List<ProfissionalViewModel>>(lstDominio);

                foreach (var model in lstModel)
                {
                    var usuario = ctx.Usuario
                        .Include(i => i.PERFIS_PROFISSIONAIS)
                        .Include(i => i.PROPOSTAS_SERVICO.Select(s => s.SERVICO))
                        .First(F => F.ID_USUARIO == model.ID_USUARIO);

                    model.NOME = usuario.NOME;
                    model.DT_NASCTO = usuario.DT_NASCIMENTO;

                    model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).DESCRICAO;

                    model.BAIRRO = usuario.BAIRRO;
                    model.CIDADE = usuario.CIDADE;
                    model.ESTADO = usuario.UF.ToString();
                    model.DT_INCLUSAO = usuario.DT_INCLUSAO;
                    model.DT_ORDENACAO = usuario.DT_ORDENACAO;

                    model.OUTROS_PERFIS = Mapper.Map<List<ProfissionalViewModel>>(usuario.PERFIS_PROFISSIONAIS.Where(w => w.ID_ESPECIALIDADE != idEspecialidade));
                    model.AVALIACOES = Mapper.Map<List<AvaliacaoViewModel>>(ctx.Avaliacao.Where(w => w.ID_USUARIO == model.ID_USUARIO & w.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE).ToList());

                    var MEUS_SERVICOS = usuario.PROPOSTAS_SERVICO.Where(w => w.ACEITA == true).Select(s => s.SERVICO);

                    if (MEUS_SERVICOS != null) model.SERVICOS.AddRange(Mapper.Map<List<ServicoViewModel_api>>(MEUS_SERVICOS));

                    foreach (var item in model.OUTROS_PERFIS)
                    {
                        item.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == item.ID_ESPECIALIDADE).DESCRICAO;
                    }
                }

                return Request.CreateResponse(HttpStatusCode.OK, lstModel);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // GET: api/Usuario
        public HttpResponseMessage Get(Guid idUsuario)
        {
            var result = ctx.PerfilProfissional.Include(i => i.ESPECIALIDADE).Where(w => w.ID_USUARIO == idUsuario).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        [HttpPost]
        public HttpResponseMessage Post(HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver(),
                    ConstructorHandling = ConstructorHandling.AllowNonPublicDefaultConstructor
                };

                var obj = JsonConvert.DeserializeObject<PERFIL_PROFISSIONAL>(values, settings);

                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.PerfilProfissional.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // PUT: api/Usuario/5
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver()
                };

                var item = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);
                var obj = JsonConvert.DeserializeObject<PERFIL_PROFISSIONAL>(values, settings);

                item.AtualizaValores(obj.RESUMO_CURRICULO);
                ctx.Entry(item).State = EntityState.Modified;

                Validate(item);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.OK);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // DELETE: api/Usuario/5
        public void Delete(Guid idUsuario, int idEspecialidade)
        {
            var item = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);

            ctx.PerfilProfissional.Remove(item);
            ctx.SaveChanges();
        }
    }
}