using System;
using System.Security.Principal;
using Microsoft.AspNet.Identity;

namespace JOB.WEB.Extensions
{
    public static class IdentityExtensions
    {
        public static Guid GetId(this IIdentity identity)
        {
            return Guid.Parse(identity.GetUserId());
        }
    }
}