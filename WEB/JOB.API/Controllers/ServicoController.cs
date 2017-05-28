using JOB.DATA;
using JOB.DATA.Domain;
using JOB.HELPERS.Validation;
using JsonNet.PrivateSettersContractResolvers;
using Newtonsoft.Json;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace JOB.API.Controllers
{
    public class ServicoController : ApiController
    {
        private Contexto ctx = new Contexto();

        // GET: api/Usuario/5
        public HttpResponseMessage Get(Guid id)
        {
            var result = ctx.Servico.FirstOrDefault(w => w.ID_SERVICO == id);

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

                var obj = JsonConvert.DeserializeObject<SERVICO>(values, settings);

                Validate(obj);
                if (!ModelState.IsValid)
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

                ctx.Servico.Add(obj);
                ctx.SaveChanges();

                return Request.CreateResponse(HttpStatusCode.Created);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
            }
        }

        //// PUT: api/Usuario/5
        //public HttpResponseMessage Put(Guid id, HttpRequestMessage request)
        //{
        //    try
        //    {
        //        var values = request.Content.ReadAsStringAsync().Result;

        //        var settings = new JsonSerializerSettings
        //        {
        //            ContractResolver = new PrivateSetterContractResolver()
        //        };

        //        var item = ctx.Servico.FirstOrDefault(w => w.ID_SERVICO == id);
        //        var obj = JsonConvert.DeserializeObject<SERVICO>(values, settings);

        //        item.AtualizaDados(obj.DS_TITULO, obj.DS_OBSERVACOES, obj.VL_SUGERIDO, obj.TEMPO_SERVICO);
        //        ctx.Entry(item).State = EntityState.Modified;

        //        Validate(item);
        //        if (!ModelState.IsValid)
        //            return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

        //        ctx.SaveChanges();

        //        return Request.CreateResponse(HttpStatusCode.OK);
        //    }
        //    catch (Exception ex)
        //    {
        //        return Request.CreateResponse(HttpStatusCode.BadRequest, ex.TratarMensagem());
        //    }
        //}

        // DELETE: api/Usuario/5
        public void Delete(Guid id)
        {
            var item = ctx.Servico.FirstOrDefault(w => w.ID_SERVICO == id);

            ctx.Servico.Remove(item);
            ctx.SaveChanges();
        }
    }
}