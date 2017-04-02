using JOB.DATA.Enum;
using System;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Models
{
    public class UsuarioViewModel
    {
        [Key]
        public int ID_USUARIO { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string NOME { get; set; }

        [Required]
        [Display(Name = "CPF")]
        public string CPF { get; set; }

        [Required]
        [Display(Name = "RG (UF)")]
        public EnumUF RgUF { get; set; }

        [Required]
        [Display(Name = "RG (NR)")]
        public string RgNR { get; set; }

        [Required]
        [Display(Name = "Dt. Nascimento")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime DT_NASCIMENTO { get; set; }

        [ScaffoldColumn(false)] //ScaffoldColumn(false) = não é gerado automaticamente nas views
        public DateTime DT_INCLUSAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_ALTERACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime? DT_APROVACAO { get; set; }

        [ScaffoldColumn(false)]
        public DateTime DT_ORDENACAO { get; set; }

        [ScaffoldColumn(false)]
        [Display(Name = "Aprovado")]
        public bool APROVADO { get; set; }
    }
}