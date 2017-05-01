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
    public class SubEspecialidadeController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario/5
        public HttpResponseMessage Get(int idEspecialidade)
        {
            var result = ctx.SubEspecialidade.Where(w => w.ID_ESPECIALIDADE == idEspecialidade).ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        //[HttpPost]
        //public HttpResponseMessage Post(HttpRequestMessage request)
        //{
        //    var values = request.Content.ReadAsStringAsync().Result;

        //    var settings = new JsonSerializerSettings
        //    {
        //        ContractResolver = new PrivateSetterContractResolver(),
        //        ConstructorHandling = ConstructorHandling.AllowNonPublicDefaultConstructor
        //    };

        //    var obj = JsonConvert.DeserializeObject<ESPECIALIDADE>(values, settings);

        //    Validate(obj);
        //    if (!ModelState.IsValid)
        //        return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

        //    ctx.Especialidade.Add(obj);
        //    ctx.SaveChanges();

        //    return Request.CreateResponse(HttpStatusCode.Created);
        //}

        //// PUT: api/Usuario/5
        //public async Task<HttpResponseMessage> Put(int id, HttpRequestMessage request)
        //{
        //    var values = request.Content.ReadAsStringAsync().Result;

        //    var settings = new JsonSerializerSettings
        //    {
        //        ContractResolver = new PrivateSetterContractResolver()
        //    };

        //    var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);
        //    var obj = JsonConvert.DeserializeObject<ESPECIALIDADE>(values, settings);

        //    item.AtualizaDados(obj.DESCRICAO, obj.EXIGIR_COMPROVACAO);
        //    ctx.Entry(item).State = EntityState.Modified;

        //    Validate(item);
        //    if (!ModelState.IsValid)
        //        return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

        //    await ctx.SaveChangesAsync();

        //    return Request.CreateResponse(HttpStatusCode.OK);
        //}

        //// DELETE: api/Usuario/5
        //public async Task Delete(int id)
        //{
        //    var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

        //    ctx.Especialidade.Remove(item);
        //    await ctx.SaveChangesAsync();
        //}
    }
}