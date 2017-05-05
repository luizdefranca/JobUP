using System;
using System.Linq;
using System.Security.Principal;
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