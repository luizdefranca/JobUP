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
    public class EspecialidadeController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario
        public HttpResponseMessage Get()
        {
            var result = ctx.Especialidade.ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        // GET: api/Usuario/5
        public HttpResponseMessage Get(int id)
        {
            var result = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        //public HttpResponseMessage GetPorUsuario(Guid id)
        //{
        //    var result = ctx.PerfilProfissional.Where(w => w.ID_USUARIO == id);

        //    return Request.CreateResponse(HttpStatusCode.OK, result);
        //}

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

                var obj = JsonConvert.DeserializeObject<ESPECIALIDADE>(values, settings);

                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Especialidade.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // PUT: api/Usuario/5
        public HttpResponseMessage Put(int id, HttpRequestMessage request)
        {
            try
            {
                var values = request.Content.ReadAsStringAsync().Result;

                var settings = new JsonSerializerSettings
                {
                    ContractResolver = new PrivateSetterContractResolver()
                };

                var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);
                var obj = JsonConvert.DeserializeObject<ESPECIALIDADE>(values, settings);

                item.AtualizaDados(obj.DESCRICAO, obj.EXIGIR_COMPROVACAO);
                ctx.Entry(item).State = EntityState.Modified;

<<<<<<< HEAD
            ctx.SaveChanges();
=======
                Validate(item);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae

                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.OK);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // DELETE: api/Usuario/5
        public void Delete(int id)
        {
            var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

            ctx.Especialidade.Remove(item);
            ctx.SaveChanges();
        }
    }
}