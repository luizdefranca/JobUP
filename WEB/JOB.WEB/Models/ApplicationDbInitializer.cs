using System.Data.Entity;

namespace AgendaCirurgicaBeta.Models
{
    public class ApplicationDbInitializer : CreateDatabaseIfNotExists<ApplicationDbContext>
    {
        protected override void Seed(ApplicationDbContext context)
        {
            InitializeIdentityForEF(context);
            base.Seed(context);
        }

        public static void InitializeIdentityForEF(ApplicationDbContext db)
        {
            //if (!db.Users.Any())
            //{
            //    var roleStore = new RoleStore<IdentityRole>(db);
            //    var roleManager = new RoleManager<IdentityRole>(roleStore);

            //    var userStore = new UserStore<ApplicationUser>(db);
            //    var userManager = new UserManager<ApplicationUser>(userStore);

            //    // Add missing roles
            //    var role = roleManager.FindByName(ConstRoles.ADMINSTRADOR_HOSPITAL);
            //    if (role == null)
            //    {
            //        foreach (var item in System.Enum.GetValues(typeof(EnumRoles)))
            //        {
            //            roleManager.Create(new IdentityRole(EnumHelper.GetEnumValue<EnumRoles>((int)item).ToString()));
            //        }
            //    }

            //    // Create test users
            //    var user = userManager.FindByName("dhiogoacioli@gmail.com");
            //    if (user == null)
            //    {
            //        var newUser = new ApplicationUser()
            //        {
            //            UserName = "dhiogoacioli@gmail.com",
            //            Email = "dhiogoacioli@gmail.com",
            //            PhoneNumber = "81988549756",
            //            EmailConfirmed = true
            //        };
            //        var result = userManager.Create(newUser, "Recaciol04$");
            //        if (result.Succeeded)
            //        {
            //            userManager.SetLockoutEnabled(newUser.Id, false);
            //            userManager.AddToRole(newUser.Id, ConstRoles.ADMINSTRADOR_SISTEMA);
            //        }
            //        else
            //        {
            //            throw new System.Exception(result.Errors.First());
            //        }
            //    }
            //}
        }
    }
}