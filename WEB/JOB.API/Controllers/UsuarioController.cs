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
    public class UsuarioController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario
        public HttpResponseMessage Get()
        {
            var result = ctx.Usuario.ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid id)
        {
            var result = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

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

            var obj = JsonConvert.DeserializeObject<USUARIO>(values, settings);

            Validate(obj);
            if (!ModelState.IsValid)
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

            try
            {
                ctx.Usuario.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // PUT: api/Usuario/5
        public HttpResponseMessage Put(Guid id, HttpRequestMessage request)
        {
            var values = request.Content.ReadAsStringAsync().Result;

            var settings = new JsonSerializerSettings
            {
                ContractResolver = new PrivateSetterContractResolver()
            };

            var item = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);
            var obj = JsonConvert.DeserializeObject<USUARIO>(values, settings);

            item.AtualizaDados(obj.NOME, obj.CPF, obj.RG, obj.DT_NASCIMENTO);
            ctx.Entry(item).State = EntityState.Modified;

            Validate(item);
            if (!ModelState.IsValid)
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

<<<<<<< HEAD
            ctx.SaveChanges();
=======
            try
            {
                ctx.SaveChanges();
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae

                return Request.CreateResponse(HttpStatusCode.OK);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        // DELETE: api/Usuario/5
        public void Delete(Guid id)
        {
            try
            {
                var item = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

<<<<<<< HEAD
            ctx.Usuario.Remove(item);
            ctx.SaveChanges();
=======
                ctx.Usuario.Remove(item);
                ctx.SaveChanges();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.TratarMensagem());
            }
>>>>>>> 0c51710b27bdb65c1917702af9a22ee6642305ae
        }
    }
}