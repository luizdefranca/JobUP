using System.Linq;
using System.Security.Claims;
using System.Security.Principal;

namespace JOB.WEB.Extensions
{
    public static class ClaimsExtensions
    {
        private static string GetHospitalID(this ClaimsIdentity identity)
        {
            return identity.Claims?.FirstOrDefault(c => c.Type == CustomClaimTypes.ID_HOSPITAL)?.Value;
        }

        private static string GetMedicoID(this ClaimsIdentity identity)
        {
            return identity.Claims?.FirstOrDefault(c => c.Type == CustomClaimTypes.ID_MEDICO)?.Value;
        }

        private static string GetPacienteID(this ClaimsIdentity identity)
        {
            return identity.Claims?.FirstOrDefault(c => c.Type == CustomClaimTypes.ID_PACIENTE)?.Value;
        }

        public static string GetHospitalID(this IIdentity identity)
        {
            var claimsIdentity = identity as ClaimsIdentity;
            return claimsIdentity != null ? GetHospitalID(claimsIdentity) : "";
        }

        public static string GetMedicoID(this IIdentity identity)
        {
            var claimsIdentity = identity as ClaimsIdentity;
            return claimsIdentity != null ? GetMedicoID(claimsIdentity) : "";
        }

        public static string GetPacienteID(this IIdentity identity)
        {
            var claimsIdentity = identity as ClaimsIdentity;
            return claimsIdentity != null ? GetPacienteID(claimsIdentity) : "";
        }
    }
}