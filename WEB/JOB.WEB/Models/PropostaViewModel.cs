using JOB.DATA.Enum;
using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class PropostaViewModel
    {
        [Key]
        public Guid ID_SERVICO { get; set; }

        [Key]
        public Guid ID_USUARIO { get; set; }

        [Display(Name = "Data Proposta")]
        public DateTime DT_PROPOSTA { get; set; }

        [Display(Name = "Duração do Serviço")]
        public EnumDuracaoServico DURACAO_SERVICO { get; set; }

        [Display(Name = "Valor da Duração do Serviço")]
        public int VALOR_DURACAO_SERVICO { get; set; }

        [Display(Name = "Valor Proposta")]
        public double VL_PROPOSTA { get; set; }

        [DataType(DataType.MultilineText)]
        [Display(Name = "Justificativa")]
        public string JUSTIFICATIVA { get; set; }

        [Display(Name = "Aceita?")]
        public bool ACEITA { get; set; }

        [Display(Name = "Título")]
        public string DS_TITULO { get; set; }

        [Display(Name = "Observações")]
        public string DS_OBSERVACOES { get; set; }
    }
}