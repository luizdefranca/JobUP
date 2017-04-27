using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using System;
using System.Data.Entity;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace JOB.WEB.Models
{
    // You can add profile data for the user by adding more properties to your ApplicationUser class, please visit http://go.microsoft.com/fwlink/?LinkID=317594 to learn more.
    public class ApplicationUser : IdentityUser
    {
        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<ApplicationUser> manager)
        {
            // Note the authenticationType must match the one defined in CookieAuthenticationOptions.AuthenticationType
            var userIdentity = await manager.CreateIdentityAsync(this, DefaultAuthenticationTypes.ApplicationCookie);
            // Add custom user claims here
            return userIdentity;
        }
    }

    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        public ApplicationDbContext()
            : base(GetConnectionString())
        {
            Database.SetInitializer(new ApplicationDbInitializer());
        }

        private static string GetConnectionString()
        {
            if (Environment.GetEnvironmentVariable("CONNECTION_STRING") != null)
            {
                return Environment.GetEnvironmentVariable("CONNECTION_STRING");
            }
            else
            {
                var path = @"c:\settings_jobup.xml";

                var xdoc = XDocument.Load(path);
                var valor = xdoc.Elements().Elements().First(f => f.Name == "CONNECTION_STRING").Value;

                return valor;
            }
        }

        public static ApplicationDbContext Create()
        {
            return new ApplicationDbContext();
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            //tipo padrão para string é varchar
            modelBuilder.Properties<string>()
                .Configure(p => p.HasColumnType("varchar"));
        }
    }
}