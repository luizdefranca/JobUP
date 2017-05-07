using System;
using System.Data.Entity;
using System.Linq;
using System.Security.Principal;
using JOB.DATA;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;

namespace JOB.WEB.Extensions
{
    public static class IdentityExtensions
    {
        public static Guid GetId(this IIdentity identity)
        {
            return Guid.Parse(identity.GetUserId());
        }

        public static int GetServicosPrivados(this IIdentity identity)
        {
            using (var ctx = new Contexto())
            {
                var id = identity.GetId();
                var user = ctx.Usuario.Include(i => i.OFERTAS_SERVICO).First(f => f.ID_USUARIO == id);
                return user.OFERTAS_SERVICO.Count(w => w.ACEITA != false);
            }
        }

        public static string GetEmailAdress(this IIdentity identity)
        {
            var userId = identity.GetUserId();
            using (var context = new ApplicationDbContext())
            {
                var user = context.Users.FirstOrDefault(u => u.Id == userId);
                return user.Email;
            }
        }
    }
}