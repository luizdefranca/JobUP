using JOB.DATA;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;
using System;
using System.Data.Entity;
using System.Linq;
using System.Security.Principal;

namespace JOB.WEB.Extensions
{
    public static class IdentityExtensions
    {
        public static Guid GetId(this IIdentity identity)
        {
            return Guid.Parse(identity.GetUserId());
        }

        public static int GetMensagensPendentes(this IIdentity identity)
        {
            using (var ctx = new Contexto())
            {
                if (identity.GetUserId() != null)
                {
                    var id = identity.GetId();
                    return ctx.Chat.Count(c => c.ID_USUARIO == id & !c.LIDA);
                }
                else
                {
                    return 0;
                }
            }
        }

        public static int GetServicosPrivados(this IIdentity identity)
        {
            using (var ctx = new Contexto())
            {
                if (identity.GetUserId() != null)
                {
                    var id = identity.GetId();
                    var user = ctx.Usuario.Include(i => i.OFERTAS_SERVICO).FirstOrDefault(f => f.ID_USUARIO == id);
                    return user != null ? user.OFERTAS_SERVICO.Count(w => w.ACEITA != false) : 0;
                }
                else
                {
                    return 0;
                }
            }
        }

        public static int GetTotalMoedas(this IIdentity identity)
        {
            using (var ctx = new Contexto())
            {
                if (identity.GetUserId() != null)
                {
                    var id = identity.GetId();
                    var user = ctx.Usuario.Include(i => i.OFERTAS_SERVICO).FirstOrDefault(f => f.ID_USUARIO == id);
                    return user != null ? user.MOEDA : 0;
                }
                else
                {
                    return 0;
                }
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

        public static string GetNome(this IIdentity identity)
        {
            var userId = identity.GetId();
            using (var context = new Contexto())
            {
                var user = context.Usuario.FirstOrDefault(u => u.ID_USUARIO == userId);
                return user.NOME;
            }
        }
    }
}