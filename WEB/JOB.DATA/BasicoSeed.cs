using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JOB.DATA
{
    public class BasicoSeed
    {
        /// <summary>
        /// Faz o Seed do Projeto juntamente com as Branchs
        /// </summary>
        /// <param name="context"></param>
        public static void Seed(Contexto context)
        {
            //var hospital = new HOSPITAL(
            //    new CNPJ("11214624000128"),
            //    "UNIMED RECIFE COOPERATIVA DE TRABALHO MÉDICO",
            //    "UNIMED RECIFE COOPERATIVA DE TRABALHO MÉDICO");

            //hospital.AdicionarUnidade(1, "UNIMED 1");
            //hospital.AdicionarUnidade(2, "UNIMED 2");
            //hospital.AdicionarUnidade(3, "UNIMED 3");

            //context.Hospital.Add(hospital);

            //var medico = new MEDICO("DHIOGO MÉDICO",
            //    new CRM(EnumUF.PE, "12345"),
            //    new DateTime(1983, 9, 9),
            //    new CPF("04820507486"),
            //    new RG(EnumUF.PE, "6902156"),
            //    EnumSexo.MASCULINO,
            //    EnumEstadoCivil.SOLTEIRO);

            //context.Medico.Add(medico);
        }
    }
}
