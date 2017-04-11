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
    public class EnderecoController : ApiController
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
            var result = ctx.Endereco.FirstOrDefault(w => w.ID_USUARIO == id);

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

            var obj = JsonConvert.DeserializeObject<ENDERECO>(values, settings);

            Validate(obj);
            if (!ModelState.IsValid)
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

            ctx.Endereco.Add(obj);
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

            var item = ctx.Endereco.FirstOrDefault(w => w.ID_USUARIO == id);
            var obj = JsonConvert.DeserializeObject<ENDERECO>(values, settings);

            item.AtualizaValores(obj.UF, obj.CEP, obj.LOGRADOURO, obj.COMPLEMENTO, obj.BAIRRO, obj.CIDADE);
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
            var item = ctx.Endereco.FirstOrDefault(w => w.ID_USUARIO == id);

            ctx.Endereco.Remove(item);
            await ctx.SaveChangesAsync();
        }
    }
}