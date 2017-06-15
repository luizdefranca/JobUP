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
    /// <summary>
    /// API de especialidades
    /// </summary>
    public class EspecialidadeController : ApiController
    {
        private Contexto ctx = new Contexto();

        /// <summary>
        /// Recupera todas as listas
        /// </summary>
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
        public HttpResponseMessage Get()
        {
            var result = ctx.Especialidade.ToList();

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Recupera uma determinada especialidade
        /// </summary>
        /// <param name="id">id da especialidade</param>
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
        public HttpResponseMessage Get(int id)
        {
            var result = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        /// <summary>
        /// Salva uma nova especialidade
        /// </summary>
        /// <param name="request">classe ESPECIALIDADE</param>
        /// <returns>retorna HttpStatusCode.Created = 201</returns>
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

        /// <summary>
        /// Atualiza uma especialidade
        /// </summary>
        /// <param name="id">id da especialidade</param>
        /// <param name="request">classe ESPECIALIDADE</param>
        /// <returns>retorna HttpStatusCode.OK = 200</returns>
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

        /// <summary>
        /// Deleta uma especialidade
        /// </summary>
        /// <param name="id">id da especialidade</param>
        public void Delete(int id)
        {
            var item = ctx.Especialidade.FirstOrDefault(w => w.ID_ESPECIALIDADE == id);

            ctx.Especialidade.Remove(item);
            ctx.SaveChanges();
        }
    }
}