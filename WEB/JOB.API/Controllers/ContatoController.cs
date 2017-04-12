using System;
using JOB.DATA;
using JOB.DATA.Domain;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class ContatoController : ApiController
    {
        private Contexto ctx = new Contexto();

        //// GET: api/Usuario
        //public HttpResponseMessage Get()
        //{
        //    var result = ctx.Endereco.ToList();

        //    return Request.CreateResponse(HttpStatusCode.OK, result);
        //}

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid id)
        {
            var result = ctx.Contato.FirstOrDefault(w => w.ID_USUARIO == id);

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

            var obj = JsonConvert.DeserializeObject<CONTATO>(values, settings);

            Validate(obj);
            if (!ModelState.IsValid)
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

            ctx.Contato.Add(obj);
            ctx.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.Created);
        }

        // PUT: api/Usuario/5
        public async Task<HttpResponseMessage> Put(Guid id, HttpRequestMessage request)
        {
            var values = request.Content.ReadAsStringAsync().Result;

            var settings = new JsonSerializerSettings
            {
                ContractResolver = new PrivateSetterContractResolver()
            };

            var item = ctx.Contato.FirstOrDefault(w => w.ID_USUARIO == id);
            var obj = JsonConvert.DeserializeObject<CONTATO>(values, settings);

            item.AtualizarValor(obj.FIXO, obj.CELULAR);
            ctx.Entry(item).State = EntityState.Modified;

            Validate(item);
            if (!ModelState.IsValid)
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

            await ctx.SaveChangesAsync();

            return Request.CreateResponse(HttpStatusCode.OK);
        }

        // DELETE: api/Usuario/5
        public async Task Delete(Guid id)
        {
            var item = ctx.Contato.FirstOrDefault(w => w.ID_USUARIO == id);

            ctx.Contato.Remove(item);
            await ctx.SaveChangesAsync();
        }
    }
}