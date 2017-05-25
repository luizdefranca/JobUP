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
    public class AvaliacaoController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade)
        {
            var result = ctx.Formacao.Where(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid idUsuario, int idEspecialidade, Guid idCliente)
        {
            var result = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);

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

                var obj = JsonConvert.DeserializeObject<AVALIACAO>(values, settings);

                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Avaliacao.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // PUT: api/Usuario/5
        public HttpResponseMessage Put(Guid idUsuario, int idEspecialidade, Guid idCliente, HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver()
                };

                var item = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);
                var obj = JsonConvert.DeserializeObject<AVALIACAO>(values, settings);

                item.AtualizaDados(obj.NOTA, obj.COMENTARIO);
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
        public void Delete(Guid idUsuario, int idEspecialidade, Guid idCliente)
        {
            var item = ctx.Avaliacao.FirstOrDefault(w => w.ID_USUARIO == idUsuario & w.ID_ESPECIALIDADE == idEspecialidade & w.ID_CLIENTE == idCliente);

            ctx.Avaliacao.Remove(item);
            ctx.SaveChanges();
        }
    }
}