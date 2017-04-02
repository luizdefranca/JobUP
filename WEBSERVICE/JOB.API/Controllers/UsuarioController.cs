using JOB.DATA;
using JOB.DATA.Domain;
using System.Collections.Generic;
using System.Data.Entity;
using System.Threading.Tasks;
using System.Web.Http;
using System.Linq;
using System.Net.Http;
using System.Net;
using System.Net.Http.Formatting;
using Newtonsoft.Json;

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
        public HttpResponseMessage Get(int id)
        {
            var result = ctx.Usuario.FirstOrDefault(w => w.ID_USUARIO == id);

            return Request.CreateResponse(HttpStatusCode.OK, result);
        }

        //// POST: api/Usuario
        //public void Post([FromBody]string value)
        //{
        //}

        [HttpPost]
        public HttpResponseMessage Post(FormDataCollection form)
        {
            var values = form.Get("values");

            var obj = new object();
            JsonConvert.PopulateObject(values, obj);

            Validate(obj);
            if (!ModelState.IsValid)
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);

            ctx.Usuario.Add((USUARIO)obj);
            ctx.SaveChanges();

            return Request.CreateResponse(HttpStatusCode.Created);
        }

        // PUT: api/Usuario/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Usuario/5
        public void Delete(int id)
        {
        }
    }
}