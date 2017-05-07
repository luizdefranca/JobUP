using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class EspecialidadeViewModel
    {
        [Key]
        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Descrição")]
        public string DESCRICAO { get; set; }

        [Display(Name = "Exige Comprovação?")]
        public bool EXIGIR_COMPROVACAO { get; set; }

        public string IMAGEM { get; set; }

        public int QTD_PROFISSIONAIS { get; set; }
    }
}