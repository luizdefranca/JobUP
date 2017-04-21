using JOB.DATA;
using System;
using System.Data.Entity;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;

namespace JOB.WEB.ApiController
{
    public class AprovarController : System.Web.Http.ApiController
    {
        public async Task<HttpResponseMessage> Get(Guid id)
        {
            using (Contexto ctx = new Contexto())
            {
                try
                {
                    var domain = await ctx.Usuario.FirstAsync(w => w.ID_USUARIO == id);

                    domain.Aprovar();
                    ctx.Entry(domain).State = EntityState.Modified;
                    await ctx.SaveChangesAsync();

                    return Request.CreateResponse(HttpStatusCode.OK, "Success");
                }
                catch (Exception e)
                {
                    return Request.CreateResponse(HttpStatusCode.BadRequest, new HttpError(e.Message));
                }
            }
        }
    }
}