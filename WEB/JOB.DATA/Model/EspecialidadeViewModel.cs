using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class EspecialidadeViewModel
    {
        [Key]
        public int ID_ESPECIALIDADE { get; set; }

        [Display(Name = "Descrição")]
        public string DESCRICAO { get; set; }

        /// <summary>
        /// Especialidade exige comprovante por parte do profissional
        /// </summary>
        [Display(Name = "Exige Comprovação?")]
        public bool EXIGIR_COMPROVACAO { get; set; }

        /// <summary>
        /// icone para ser exibido no sistema
        /// </summary>
        public string IMAGEM { get; set; }

        /// <summary>
        /// Quantidade de profissionais cadastrados para esta especialidade
        /// </summary>
        public int QTD_PROFISSIONAIS { get; set; }
    }
}