using JOB.DATA;
using JOB.DATA.Domain;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class UsuarioFullController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario
        public HttpResponseMessage Get()
        {
            var result = ctx.Usuario.Include(i => i.ENDERECO).Include(i => i.CONTATO).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid id)
        {
            var result = ctx.Usuario.Include(i => i.ENDERECO).Include(i => i.CONTATO).FirstOrDefault(w => w.ID_USUARIO == id);

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

            ctx.Usuario.Add(obj);
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

            var item = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);
            var obj = JsonConvert.DeserializeObject<USUARIO>(values, settings);

            item.AtualizaDados(obj.NOME, obj.CPF, obj.RG, obj.DT_NASCIMENTO);
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
            var item = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

            ctx.Usuario.Remove(item);
            await ctx.SaveChangesAsync();
        }
    }
}