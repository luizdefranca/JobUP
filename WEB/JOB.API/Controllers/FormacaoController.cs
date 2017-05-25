using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;

using System.Web.Http;

namespace JOB.API.Controllers
{
    public class FormacaoController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.Formacao.Where(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade, int idFormacao)
        {
            var result = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

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

                var obj = JsonConvert.DeserializeObject<FORMACAO>(values, settings);

                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Formacao.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception)
            {

                throw;
            }

        }

        // PUT: api/Usuario/5
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, int idFormacao, HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver()
                };

                var item = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);
                var obj = JsonConvert.DeserializeObject<FORMACAO>(values, settings);

                item.AtualizaDados(obj.INSTITUICAO, obj.NOME_CURSO, obj.ANO_FORMACAO);
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
        public void Delete(Guid idUsuario, int idEspecialidade, int idFormacao)
        {
            var item = ctx.Formacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_FORMACAO == idFormacao);

            ctx.Formacao.Remove(item);
            ctx.SaveChanges();
        }
    }
}