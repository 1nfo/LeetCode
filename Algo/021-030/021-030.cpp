class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode * head = new ListNode(0), * curr=head;
        while (l1 && l2){
            if (l1->val>l2->val)curr->next=l2,l2=l2->next;
            else curr->next=l1,l1=l1->next;
            curr=curr->next;
        }
        if (l1) curr->next=l1;
        if (l2) curr->next=l2;
        curr=head;
        head=head->next;
        delete curr;
        return head;
    }
};

// 022
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<vector<string>> ret;
        ret.push_back(vector<string>()={""});
        for (int i=0;i<n;i++){
            vector<string> tmp;
            for (int j=0;j<=i;j++){
                for(string a:ret[j]){
                    for(string b:ret[i-j]){
                        tmp.push_back("("+a+")"+b);
                    }
                }
            }
            ret.push_back(tmp);
        }
        return ret[n];
    }
};

// 023
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.size()==0)return NULL;
        vector<ListNode*> *lists2=&lists;
        while(lists2->size()>1){
            vector<ListNode*>* tmp=new vector<ListNode*>(),*del;
            for(int i=0;i<lists2->size()/2;i++){
                tmp->push_back(merge((*lists2)[i*2],(*lists2)[i*2+1]));
            }
            if (lists2->size()%2) tmp->push_back((*lists2)[lists2->size()-1]);
            del=lists2;
            lists2=tmp;
            if (del!=&lists)delete del;
        }
        return (*lists2)[0];
    }
    ListNode* merge(ListNode* a,ListNode* b){
        ListNode* head=new ListNode(0),*curr;
        curr=head;
        while(a && b){
            if (a->val>b->val) curr->next=b,b=b->next;
            else curr->next=a,a=a->next;
            curr=curr->next;
        }
        if(a)curr->next=a;
        if(b)curr->next=b;
        curr=head,head=head->next;
        delete curr;
        return head;
    }
};
