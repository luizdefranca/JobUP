using System;

namespace JOB.WEB.Models
{
    public class ListaChatVM
    {
        public Guid ID_SERVICO { get; set; }
        [Display(Name = "Descrição do Serviço")]
        public string DESC_SERVICO { get; set; }
        [Display(Name = "Mensagens Não Lidas")]
        public int QTD_NAO_LIDAS { get; set; }
    }

    public class ChatVM
    {
        public Guid ID_SERVICO { get; set; }
        public DateTime DT_MENSAGEM { get; set; }
        public Guid ID_USUARIO { get; set; }
        public string NOME_USUARIO { get; set; }
        public string MENSAGEM { get; set; }
        public bool LIDA { get; set; }
    }
}