﻿using JOB.DATA;
using JOB.DATA.Domain;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using AutoMapper;
using JOB.WEB.Models;

namespace JOB.API.Controllers
{
    public class PerfilProfissionalController : ApiController
    {
        private Contexto ctx = new Contexto();

        public HttpResponseMessage Get()
        {
            var lstDominio = ctx.PerfilProfissional.ToList();

            var lstModel = Mapper.Map<List<ProfissionalViewModel>>(lstDominio);

            foreach (var model in lstModel)
            {
                model.NOME = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).NOME;
                model.DT_NASCTO = ctx.Usuario.First(f => f.ID_USUARIO == model.ID_USUARIO).DT_NASCIMENTO;
                model.DESC_ESPECIALIDADE = ctx.Especialidade.First(f => f.ID_ESPECIALIDADE == model.ID_ESPECIALIDADE)
                    .DESCRICAO;
            }

            return Request.CreateResponse(HttpStatusCode.OK, lstModel);
        }

        // GET: api/Usuario
        public HttpResponseMessage Get(Guid idUsuario)
        {
            var result = ctx.PerfilProfissional.Where(w => w.ID_USUARIO == idUsuario).ToList();

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

        // PUT: api/Usuario/5
        public async Task<HttpResponseMessage> Put(Guid idUsuario, int idEspecialidade, HttpRequestMessage request)
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

            await ctx.SaveChangesAsync();

            return Request.CreateResponse(HttpStatusCode.OK);
        }

        // DELETE: api/Usuario/5
        public async Task Delete(Guid idUsuario, int idEspecialidade)
        {
            var item = ctx.PerfilProfissional.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade);

            ctx.PerfilProfissional.Remove(item);
            await ctx.SaveChangesAsync();
        }
    }
}