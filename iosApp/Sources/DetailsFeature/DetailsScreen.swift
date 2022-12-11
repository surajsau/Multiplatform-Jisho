//
//  DetailsScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import Utils
import shared

public struct DetailsScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    @ObservedObject var nativeVM = NativeViewModel(viewModel: DetailsViewModel.init())
    
    private let id: Int64
    private let word: String
    
    public init(id: Int64, word: String) {
        self.id = id
        self.word = word
    }
    
    public var body: some View {
        ZStack {
            VStack {
                
                if let state = nativeVM.state {
                    
                    ScrollView(showsIndicators: false) {
                        VStack(spacing: 16) {
                            
                            DetailsHeader(model: state.header)
                            
                            if state.showAlternative {
                                DetailsAlternatives(alternative: state.alternatives)
                            }
                            
                            if state.showKanji {
                                DetailsKanji(items: state.kanjis)
                            }
                            
                            if state.showMeanings {
                                DetailsMeanings(meanings: state.meanings)
                            }
                            
                            if state.showSentences {
                                DetailsSentence(model: state.sentences, word: self.word)
                            }
                            
                            if state.showConjugation {
                                DetailsConjugation(model: state.conjugations!)
                            }
                        }
                        .padding()
                    }
                }
                
                Spacer()
            }
        }
        .onAppear {
            nativeVM.viewModel.doInitWith(id: id, word: word)
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
